package Releases_ApacheIgniteNightly.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

object Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries : BuildType({
    name = "[APACHE IGNITE NIGHTLY RELEASE] #2 :: Assemble Binaries"

    artifactRules = """
        target/bin/*.zip => .
        modules/platforms/dotnet/nupkg/*.nupkg => apache-ignite-%IGNITE_VERSION%-nuget-staging.zip
        modules/web-console/web-agent/target/ignite-web-agent-%IGNITE_VERSION%.zip => .
        staging => apache-ignite-%IGNITE_VERSION%-maven-staging.zip
        modules/web-console/frontend => web-console.zip!/frontend
        modules/web-console/backend => web-console.zip!/backend
    """.trimIndent()

    params {
        text("env.JAVA_HOME", "%env.JDK_ORA_8%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        password("MYGET_PASSWORD", "zxx90a9b89d006d43dc655e5289158b20b3efc4745b263047f7055fb6011affde957fc082dc66ee48bd", display = ParameterDisplay.HIDDEN)
        text("IGNITE_VERSION", "%dep.Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.IGNITE_VERSION%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(RelativeId("GitHubApacheIgnite"))

        cleanCheckout = true
    }

    steps {
        script {
            name = "Prepare custom settings.xml"
            enabled = false
            scriptContent = """
                #!/usr/bin/env bash
                set -x
                
                cat <<EOF > settings.xml
                <?xml version="1.0" encoding="UTF-8"?>
                <settings>
                  <servers>
                    <server>
                      <id>myget</id>
                      <username>apache-ignite</username>
                      <password>\${'$'}{myget.password}</password>
                    </server>
                    <server>
                      <id>local-proxy</id>
                    </server>
                  </servers>
                  <profiles>
                    <profile>
                      <id>artifactory</id>
                      <repositories>
                        <repository>
                          <snapshots>
                            <enabled>false</enabled>
                          </snapshots>
                          <id>local-proxy</id>
                          <name>local-proxy</name>
                          <url>http://10.100.100.250:8081/nexus</url>
                        </repository>
                      </repositories>
                      <pluginRepositories>
                        <pluginRepository>
                          <snapshots>
                            <enabled>false</enabled>
                          </snapshots>
                          <id>local-proxy</id>
                          <name>local-proxy</name>
                          <url>http://10.100.100.250:8081/nexus</url>
                        </pluginRepository>
                      </pluginRepositories>
                    </profile>
                  </profiles>
                  <activeProfiles>
                    <activeProfile>artifactory</activeProfile>
                  </activeProfiles>
                </settings>
                EOF
            """.trimIndent()
        }
        maven {
            name = "Deploy to MyGet"
            enabled = false
            goals = "deploy"
            pomLocation = ""
            runnerArgs = """
                -T 2C
                -pl -:ignite-osgi-karaf -am
                -Pall-java,all-scala,licenses
                -DclientDocs
                -DskipTests
                -Dmyget.password=%MYGET_PASSWORD%
                -DaltDeploymentRepository=myget::default::https://www.myget.org/F/apache-ignite-nightly/maven/
            """.trimIndent()
            userSettingsSelection = "userSettingsSelection:byPath"
            userSettingsPath = "settings.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = """
                -Xmx1g
                -XX:MaxPermSize=512m
            """.trimIndent()
        }
        maven {
            name = "Build"
            goals = "package"
            pomLocation = ""
            runnerArgs = """
                -pl -:ignite-osgi-karaf -am
                -Pall-java,all-scala,licenses
                -DclientDocs
                -DskipTests
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            userSettingsPath = "settings.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = """
                -Xmx1g
                -XX:MaxPermSize=512m
            """.trimIndent()
        }
        maven {
            name = "Deploy to Local Staging"
            goals = "deploy"
            pomLocation = ""
            runnerArgs = """
                -Pall-java,all-scala,licenses
                -DclientDocs
                -DskipTests
                -DaltDeploymentRepository=local::default::file:staging
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = """
                -Xmx1g
                -XX:MaxPermSize=512m
            """.trimIndent()
        }
        maven {
            name = "Generate Javadoc"
            goals = "validate"
            runnerArgs = "-Pjavadoc"
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        powerShell {
            name = "Build .NET binaries."
            scriptMode = file {
                path = "modules/platforms/dotnet/build.ps1"
            }
            param("jetbrains_powershell_scriptArguments", """
                -skipJava
                -skipExamples
                -versionSuffix "-nightly"
            """.trimIndent())
        }
        maven {
            name = "Assembly Apache Ignite"
            goals = "initialize"
            pomLocation = ""
            runnerArgs = """
                -Prelease,all-scala
                -Dignite.edition=apache-ignite
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        maven {
            name = "Assembly Apache Ignite Slim"
            goals = "initialize"
            pomLocation = ""
            runnerArgs = """
                -Prelease,all-scala
                -Dignite.edition=apache-ignite-slim
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
    }

    dependencies {
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleaseBuildNetCpp")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "ignite.odbc.installers.zip!** => modules/platforms/cpp/bin/odbc"
            }
        }
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "apache-ignite-%IGNITE_VERSION%-src.zip!** => ."
            }
        }
    }

    requirements {
        contains("teamcity.agent.jvm.os.name", "Linux")
    }
})

