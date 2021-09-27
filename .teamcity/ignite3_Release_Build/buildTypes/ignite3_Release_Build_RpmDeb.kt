package ignite3_Release_Build.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object ignite3_Release_Build_RpmDeb : BuildType({
    name = "[2] RPM | DEB"

    artifactRules = "%DIR_PACKAGES% => %DIR_PACKAGES%"

    params {
        text("DIR_BINARIES", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("DIR_PACKAGES", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("DIR_RPM_BUILD", "deliveries/rpm", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite3)

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
    }

    steps {
        script {
            name = "Build RPM"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                # Prepare
                cp -rfv %DIR_BINARIES%/ignite deliveries/rpm/
                cd deliveries/rpm/
                PACKAGE_VERSION="${'$'}(grep -E '^\*' apache-ignite.spec | head -1 | sed -r 's|.*\s-\s||')"
                
                
                # Build package
                bash build.sh "${'$'}{PACKAGE_VERSION}"
            """.trimIndent()
        }
        script {
            name = "Prepare artifacts"
            scriptContent = """
                #!/usr/bin/env bash
                set -o nounset; set -o errexit; set -o pipefail; set -o errtrace; set -o functrace
                set -x
                
                
                mkdir -pv %DIR_PACKAGES%
                
                cp -rfv %DIR_RPM_BUILD%/*.rpm \
                        %DIR_PACKAGES%/
            """.trimIndent()
        }
    }

    dependencies {
        dependency(ignite3_Release_Build_BinariesMavenJavadoc) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = "%DIR_BINARIES% => %DIR_BINARIES%"
            }
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
