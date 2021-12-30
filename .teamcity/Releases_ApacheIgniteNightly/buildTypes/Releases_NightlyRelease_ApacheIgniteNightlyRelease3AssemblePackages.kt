package Releases_ApacheIgniteNightly.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

object Releases_NightlyRelease_ApacheIgniteNightlyRelease3AssemblePackages : BuildType({
    name = "[APACHE IGNITE NIGHTLY RELEASE] #3 :: Assemble Linux Packages"

    artifactRules = """
        packaging/*.rpm
        packaging/*.deb
    """.trimIndent()

    params {
        text("IGNITE_VERSION", "%dep.Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.IGNITE_VERSION%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(RelativeId("GitHubApacheIgnite"))
    }

    steps {
        script {
            scriptContent = """
                #!/usr/bin/env bash
                set -x
                
                #
                # Build packages
                #
                packaging/package.sh --rpm --deb
            """.trimIndent()
        }
    }

    dependencies {
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = "apache-ignite-%IGNITE_VERSION%-bin.zip => packaging"
            }
        }
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = "apache-ignite-%IGNITE_VERSION%-src.zip!** => ."
            }
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})

