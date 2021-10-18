package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleDockerImage'
in the project with id = 'Releases_ApacheIgniteNightly', and delete the patch script.
*/
create(RelativeId("Releases_ApacheIgniteNightly"), BuildType({
    id("Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleDockerImage")
    name = "[APACHE IGNITE NIGHTLY RELEASE] #3 :: Assemble Docker Image"

    artifactRules = "*.tar.gz"

    params {
        param("WEBAGENT_ARCHIVE_NAME", "web-agent-%IGNITE_VERSION%-docker-image")
        text("DOCKER_ARCHIVE_NAME", "apache-ignite-%IGNITE_VERSION%-docker-image", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("IGNITE_VERSION", "%dep.Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.IGNITE_VERSION%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("WEBCONSOLE_ARCHIVE_NAME", "web-console-standalone-%IGNITE_VERSION%-docker-image")
        text("DIR__DOCKERFILE", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(RelativeId("GitHubApacheIgnite"))

        cleanCheckout = true
    }

    steps {
        script {
            name = "Prepare"
            enabled = false
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                DIR__DOCKERFILE="docker/apache-ignite"
                DIR__NEW_DOCKERFILE="deliveries/docker/apache-ignite/x86_64"
                if [ -d "${'$'}{DIR__NEW_DOCKERFILE}" ]; then
                	DIR__DOCKERFILE="${'$'}{DIR__NEW_DOCKERFILE}"
                    cp -rfv "${'$'}{DIR__DOCKERFILE}/../run.sh" "${'$'}{DIR__DOCKERFILE}"
                fi
                cp -rf apache-ignite ${'$'}{DIR__DOCKERFILE}/
                echo "##teamcity[setParameter name='DIR__DOCKERFILE' value='${'$'}{DIR__DOCKERFILE}']"
            """.trimIndent()
        }
        step {
            name = "Assemble Apache Ignite Docker image"
            type = "DockerBuildRemote"
            enabled = false
            executionMode = BuildStep.ExecutionMode.DEFAULT
            param("DOCKER_TAG_NAME", "apacheignite/ignite")
            param("DOCKER_ARCHIVE_NAME", "%DOCKER_ARCHIVE_NAME%")
            param("DOCKER_TAG_VERSION", "%IGNITE_VERSION%")
            param("DOCKER_SRC_DIR", "%DIR__DOCKERFILE%")
        }
    }

    dependencies {
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = "apache-ignite-%IGNITE_VERSION%-bin.zip!apache-ignite-%IGNITE_VERSION%-bin/** => apache-ignite"
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
}))

