package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Build : BuildType({
    name = "> Build"
    description = "Build and prepare project for testing"

    artifactRules = """
        ignite.zip
        repository.zip
        **/checkstyle-result.xml => checkstyle.zip
        **/target/rat.txt => rat.zip
    """.trimIndent()

    params {
        checkbox("FLAG_RUN_SANITY_CHECKS", "true", label = "Run Sanity Checks", description = "Check Licenses, Javadoc and Code Style along the main Build", display = ParameterDisplay.PROMPT,
                  checked = "true", unchecked = "false")
        text("JVM_EXTRA_ARGS", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
    }

    steps {
        script {
            name = "Clean up local Maven repository"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                rm -rfv ~/.m2/repository/org/apache/ignite
            """.trimIndent()
        }
        maven {
            name = "Build Apache Ignite"
            goals = "install"
            pomLocation = ""
            runnerArgs = """
                -U
                -P %MAVEN_PROFILES%,check-test-suites
                -DskipTests
                -Dmaven.javadoc.skip=true
                -Dmaven.source.skip=true
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
        maven {
            name = "Check Code Style"

            conditions {
                equals("FLAG_RUN_SANITY_CHECKS", "true")
            }
            goals = "checkstyle:checkstyle"
            pomLocation = ""
            runnerArgs = "-P %MAVEN_PROFILES%,checkstyle"
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
        maven {
            name = "Check Javadoc"

            conditions {
                equals("FLAG_RUN_SANITY_CHECKS", "true")
            }
            goals = "initialize"
            pomLocation = ""
            runnerArgs = "-P javadoc"
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
        maven {
            name = "Check License Headers"

            conditions {
                equals("FLAG_RUN_SANITY_CHECKS", "true")
            }
            goals = "validate"
            pomLocation = ""
            runnerArgs = "-P check-licenses"
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
        maven {
            name = "Check Missing Tests (prepare modules)"

            conditions {
                equals("FLAG_RUN_SANITY_CHECKS", "true")
            }
            goals = "surefire:test"
            pomLocation = ""
            runnerArgs = "-P %MAVEN_PROFILES%,check-test-suites"
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
        maven {
            name = "Check Missing Tests"

            conditions {
                equals("FLAG_RUN_SANITY_CHECKS", "true")
            }
            goals = "test"
            pomLocation = ""
            runnerArgs = """
                -N
                -P %MAVEN_PROFILES%,check-test-suites
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
        script {
            name = "Prepare built artifacts"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                # Prepare archive with 'target' directories
                zip -r ignite target modules/*/target -x '*.jar'
                zip -ur ignite modules/*/target -i */aspectjweaver-*.jar
                
                
                # Prepare archive with installed artifacts
                mkdir -pv repository/org/apache/ignite
                cp -rfv ~/.m2/repository/org/apache/ignite/* repository/org/apache/ignite/
                zip -r repository repository
            """.trimIndent()
        }
    }

    failureConditions {
        executionTimeoutMin = 30
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
        startsWith("teamcity.agent.name", "aitc")
    }
})
