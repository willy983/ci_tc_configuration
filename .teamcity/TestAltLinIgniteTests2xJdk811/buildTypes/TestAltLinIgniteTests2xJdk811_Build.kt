package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object TestAltLinIgniteTests2xJdk811_Build : BuildType({
    name = "> Build"
    description = "Build / Check / Prepare project for testing"

    artifactRules = """
        ignite.zip
        repository.zip
        run.zip
        **/checkstyle-result.xml => checkstyle.zip
        **/target/rat.txt => rat.zip
    """.trimIndent()

    params {
        text("FLAG_RUN_SANITY_CHECKS", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("JVM_EXTRA_ARGS", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

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
                
                REPOSITORY__DIR="%env.HOME%/.m2/repository/org/apache/ignite"
                REPOSITORY__DIR__TEST="%env.HOME%/.m2/repository/com/sbt/ignite"
                
                if [ -d "${'$'}{REPOSITORY__DIR__TEST}" ]
                then
                	rm -rfv "${'$'}{REPOSITORY__DIR__TEST}"
                fi
                
                if [ -d "${'$'}{REPOSITORY__DIR}" ]
                then
                	rm -rfv "${'$'}{REPOSITORY__DIR}"
                fi
                                
            """.trimIndent()
        }
        maven {
            name = "Build Apache Ignite [JDK8]"
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
            name = "Check Code Style [JDK8]"

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
            name = "Check Javadoc [JDK8]"

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
            name = "Check License Headers [JDK8]"

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
            name = "Check Missing Tests (prepare modules) [JDK8]"

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
            name = "Check Missing Tests [JDK8]"

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
            name = "Prepare ignite-tools for rebuilding"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                rm -rfv modules/tools/target
            """.trimIndent()
        }
        maven {
            name = "Rebuild ignite-tools (to restore default surefire provider) [JDK8]"
            goals = "install"
            pomLocation = ""
            runnerArgs = """
                -pl :ignite-tools -am
                -DskipTests
                -Dmaven.javadoc.skip
                -Dmaven.source.skip
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
                zip -r ignite target examples/target modules/{*,*/*}/target -x '*.jar'
                zip -ur ignite modules/{*,*/*}/target -i */aspectjweaver-*.jar \
                                                      -i */cache-api-*.jar \
                                                      -i */h2-*.jar
                if [ -d "modules/calcite" ]; then                                      
                	zip -ur ignite modules/calcite/target/libs/calcite-*.jar \
                	               modules/calcite/target/libs/avatica-*.jar \
                	               modules/calcite/target/libs/checker-qual-*.jar \
                	               modules/calcite/target/libs/commons-compiler-*.jar \
                	               modules/calcite/target/libs/esri-geometry-*.jar \
                	               modules/calcite/target/libs/guava-*.jar \
                	               modules/calcite/target/libs/janino-*.jar \
                	               modules/calcite/target/libs/json-path-*.jar
                fi
                zip -r run modules/ssh/target/libs/jsch-*.jar \
                           modules/zookeeper/target/libs/*.jar \
                           modules/spring/target/libs/spring-*.jar \
                           modules/spring/target/libs/commons-logging-*.jar \
                           modules/jta/target/libs/jta-*.jar \
                           modules/schedule/target/libs/cron4j-*.jar
                zip -ur run modules/direct-io/target/libs/jna-*.jar \
                            modules/log4j2/target/libs/log4j-*.jar \
                            modules/direct-io/target/ignite-direct-io-*.jar \
                            modules/indexing/target/libs/lucene-*.jar
                zip -ur run modules/rest-http/target/libs/jetty-*.jar \
                            modules/rest-http/target/libs/jackson-*.jar \
                            modules/ducktests/target/libs/servlet-*.jar
                
                
                # Prepare archive with installed artifacts
                REPOSITORY__DIR="%env.HOME%/.m2/repository/org/apache/ignite"
                REPOSITORY__DIR__TEST="%env.HOME%/.m2/repository/com/sbt/ignite"
                
                if [ -d "${'$'}{REPOSITORY__DIR}" ]
                then
                    mkdir -pv repository/org/apache/ignite
                    cp -rfv ${'$'}{REPOSITORY__DIR}/* repository/org/apache/ignite/
                fi
                
                if [ -d "${'$'}{REPOSITORY__DIR__TEST}" ]
                then
                	REPOSITORY__DIR=${'$'}{REPOSITORY__DIR__TEST}
                    mkdir -pv repository/com/sbt/ignite
                    cp -rfv ${'$'}{REPOSITORY__DIR}/* repository/com/sbt/ignite/
                fi                
                
                zip -r repository repository
            """.trimIndent()
        }
    }

    failureConditions {
        executionTimeoutMin = 30
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
        startsWith("teamcity.agent.name", "altLin")
    }
})
