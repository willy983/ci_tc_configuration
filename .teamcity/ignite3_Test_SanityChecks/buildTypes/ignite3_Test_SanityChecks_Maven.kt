package ignite3_Test_SanityChecks.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnText

object ignite3_Test_SanityChecks_Maven : BuildType({
    name = "Maven"
    description = "Checks Maven project is configured correctly"

    artifactRules = "%FILE_UNUSED_PROPERTIES%"

    params {
        text("FILE_UNUSED_PROPERTIES", "unused-properties.txt", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("DIR__MAVEN_CHECK_SCRIPTS", "check-rules/maven-check-scripts", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("ERROR_TEXT__UNUSED_DECLARATION", "[ERROR] Found unused declaration", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("ERROR_TEXT__UNUSED_PROPERTY", "[ERROR] Found unused property", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("ERROR_TEXT__UNSORTED_MODULES", "[ERROR] Modules are in unsorted order", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("ERROR_TEXT__PROPERTIES_SECTION", "[ERROR] Found <properties> section", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("ERROR_TEXT__VERSION_IN_NON_PARENT_POM", "[ERROR] Found version in non parent pom", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite3)

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
    }

    steps {
        script {
            name = "Check dependency and plugin versions not in parent"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                FILE__MAVEN_CHECK_SCRIPT="%DIR__MAVEN_CHECK_SCRIPTS%/CheckDependencyAndPluginVersionsNotInParent.sh"
                if [ -f "${'$'}{FILE__MAVEN_CHECK_SCRIPT}" ]; then
                	bash -x "${'$'}{FILE__MAVEN_CHECK_SCRIPT}"
                else
                	find . -name "pom.xml" |  \
                	  grep -v parent | \
                	  while read -r pom; do
                	    for xpath in "project/dependencies/dependency/version" \
                	                 "project/build/plugins/plugin/version" \
                	                 "project/profiles/profile/build/plugins/plugin/version"; do
                			if xpath -e "${'$'}{xpath}" ${'$'}{pom} 2>&1 | \
                	          grep -qE "^Found"; then
                	        	echo "%ERROR_TEXT__VERSION_IN_NON_PARENT_POM%: ${'$'}{pom}"
                			fi
                	    done
                	done
                fi
            """.trimIndent()
        }
        script {
            name = "Check unused dependencies and plugins in parent"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                FILE__MAVEN_CHECK_SCRIPT="%DIR__MAVEN_CHECK_SCRIPTS%/CheckUnusedDependenciesAndPluginsInParent.sh"
                if [ -f "${'$'}{FILE__MAVEN_CHECK_SCRIPT}" ]; then
                	bash -x "${'$'}{FILE__MAVEN_CHECK_SCRIPT}"
                else
                	POMS=${'$'}(find . -name pom.xml | grep -v parent)
                	for xpath in "project/dependencyManagement/dependencies/dependency/artifactId/text()" \
                	             "project/build/pluginManagement/plugins/plugin/artifactId/text()"; do
                		xpath -e "${'$'}{xpath}" parent/pom.xml 2>&1 | \
                	      grep -vE '(NODE|Found)' | \
                	      while read -r declaration; do
                	        FOUND=false
                	        for pom in ${'$'}{POMS}; do
                	        	if grep -E "<artifactId>${'$'}{declaration}</artifactId>" "${'$'}{pom}"; then
                	            	FOUND=true
                	                continue 2
                	            fi
                	        done
                	        for parent_xpath in "project/build/plugins" \
                	                            "project/dependencies"; do
                	            if xpath -e "${'$'}{parent_xpath}" parent/pom.xml 2>&1 | \
                	              grep -E "<" | \
                	              grep -E "<artifactId>${'$'}{declaration}</artifactId>"; then
                	              	FOUND=true
                	                continue 2
                	            fi
                	        done
                	        if [ "${'$'}{FOUND}" == "false" ]; then
                	            echo "${'$'}{declaration}" >> %FILE_UNUSED_PROPERTIES%
                	            echo "%ERROR_TEXT__UNUSED_DECLARATION%: ${'$'}{declaration}"
                	        fi
                	    done
                	done
                fi
            """.trimIndent()
        }
        script {
            name = "Check properties not in parent"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                FILE__MAVEN_CHECK_SCRIPT="%DIR__MAVEN_CHECK_SCRIPTS%/CheckPropertiesNotInParent.sh"
                if [ -f "${'$'}{FILE__MAVEN_CHECK_SCRIPT}" ]; then
                	bash -x "${'$'}{FILE__MAVEN_CHECK_SCRIPT}"
                else
                	find . -name "pom.xml" | \
                	  grep -v parent | \
                	  while read -r pom; do
                	      if grep '<properties>' "${'$'}{pom}"; then
                	          echo "%ERROR_TEXT__PROPERTIES_SECTION%: ${'$'}{pom}"
                	      fi
                	done
                fi
            """.trimIndent()
        }
        script {
            name = "Check unused properties"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                FILE__MAVEN_CHECK_SCRIPT="%DIR__MAVEN_CHECK_SCRIPTS%/CheckUnusedProperties.sh"
                if [ -f "${'$'}{FILE__MAVEN_CHECK_SCRIPT}" ]; then
                	bash -x "${'$'}{FILE__MAVEN_CHECK_SCRIPT}"
                else
                	POMS=${'$'}(find . -name pom.xml)
                	xpath -e "project/properties/*" parent/pom.xml 2>&1 | \
                	  grep -E "^<.*\.version>" | \
                	  sed -r 's|<(.*)>.*<\/.*>|\1|' | \
                	  while read -r property; do
                	    FOUND=false
                	    for pom in ${'$'}{POMS}; do
                	        if grep -qE "\\${'$'}.*${'$'}{property}" "${'$'}{pom}"; then
                	            FOUND=true
                   	     fi
                	    done
                	    if [ "${'$'}{FOUND}" == "false" ]; then
                   	    	echo "${'$'}{property}" > %FILE_UNUSED_PROPERTIES%
                	        echo "%ERROR_TEXT__UNUSED_PROPERTY%: ${'$'}{property}"
                	    fi
                	done
                fi
            """.trimIndent()
        }
        script {
            name = "Check modules in root pom.xml are sorted"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                FILE__MAVEN_CHECK_SCRIPT="%DIR__MAVEN_CHECK_SCRIPTS%/CheckModulesInRootPomAreSorted.sh"
                if [ -f "${'$'}{FILE__MAVEN_CHECK_SCRIPT}" ]; then
                	bash -x "${'$'}{FILE__MAVEN_CHECK_SCRIPT}"
                else
                	xpath -e "project/modules/module/text()" pom.xml 2>/dev/null > current-list
                	cat current-list | sort -h > sorted-list
                	DIFF="${'$'}(diff current-list sorted-list || true)"
                	if [ "${'$'}{DIFF}" != "" ]; then
                		echo "%ERROR_TEXT__UNSORTED_MODULES%:"
                	    echo "${'$'}{DIFF}"
                	fi
                fi
            """.trimIndent()
        }
        maven {
            name = "Check that project is successfully built with -Dmaven.test.skip flag"
            goals = "package"
            runnerArgs = "-Dmaven.test.skip"
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_11%"
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "%ERROR_TEXT__PROPERTIES_SECTION%"
            failureMessage = "%ERROR_TEXT__PROPERTIES_SECTION%"
            reverse = false
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "%ERROR_TEXT__UNSORTED_MODULES%"
            failureMessage = "%ERROR_TEXT__UNSORTED_MODULES%"
            reverse = false
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "%ERROR_TEXT__UNUSED_DECLARATION%"
            failureMessage = "%ERROR_TEXT__UNUSED_DECLARATION%"
            reverse = false
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "%ERROR_TEXT__UNUSED_PROPERTY%"
            failureMessage = "%ERROR_TEXT__UNUSED_PROPERTY%"
            reverse = false
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "%ERROR_TEXT__VERSION_IN_NON_PARENT_POM%"
            failureMessage = "%ERROR_TEXT__VERSION_IN_NON_PARENT_POM%"
            reverse = false
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
