package ignite2_Release_ApacheIgniteNightly.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object ignite2_Release_NightlyRelease_RunApacheIgniteNightlyRelease : BuildType({
    name = "-> Run :: Apache Ignite Nightly Release"

    artifactRules = "*.zip"
    type = BuildTypeSettings.Type.COMPOSITE

    params {
        text("IGNITE_VERSION", "${ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleasePrepare.depParamRefs["IGNITE_VERSION"]}", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        cleanCheckout = true
        showDependenciesChanges = true
    }

    triggers {
        schedule {
            schedulingPolicy = daily {
                hour = 23
            }
            branchFilter = """
                +:<default>
                +:ignite-2.12
            """.trimIndent()
            triggerBuild = always()
            withPendingChangesOnly = false
            enableQueueOptimization = false
            param("revisionRuleBuildBranch", "<default>")

            enforceCleanCheckoutForDependencies = true
        }
    }

    dependencies {
        dependency(ignite2_Release_NightlyRelease_ApacheIgniteNightlyRelease3AssemblePackages) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = """
                    *.rpm
                    *.deb
                """.trimIndent()
            }
        }
        dependency(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "*.zip"
            }
        }
        dependency(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleaseAssembleDockerImage) {
            snapshot {
                onDependencyFailure = FailureAction.IGNORE
                onDependencyCancel = FailureAction.IGNORE
            }

            artifacts {
                cleanDestination = true
                artifactRules = "*.tar.gz"
            }
        }
        dependency(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleasePrepare) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "apache-ignite-%IGNITE_VERSION%-src.zip"
            }
        }
    }
})
