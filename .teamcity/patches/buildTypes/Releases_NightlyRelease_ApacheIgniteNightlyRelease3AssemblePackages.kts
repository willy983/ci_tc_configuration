package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Releases_NightlyRelease_ApacheIgniteNightlyRelease3AssemblePackages'
in the project with id = 'Releases_ApacheIgniteNightly', and delete the patch script.
*/
create(RelativeId("Releases_ApacheIgniteNightly"), BuildType({
    id("Releases_NightlyRelease_ApacheIgniteNightlyRelease3AssemblePackages")
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
}))

