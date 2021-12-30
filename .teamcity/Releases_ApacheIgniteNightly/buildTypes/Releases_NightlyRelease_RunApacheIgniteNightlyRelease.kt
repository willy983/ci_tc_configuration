package Releases_ApacheIgniteNightly.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

object Releases_NightlyRelease_RunApacheIgniteNightlyRelease : BuildType({
    name = "-> Run :: Apache Ignite Nightly Release"

    artifactRules = "*.zip"
    type = BuildTypeSettings.Type.COMPOSITE

    params {
        text("IGNITE_VERSION", "%dep.Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.IGNITE_VERSION%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
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
                +:ignite-2.11
            """.trimIndent()
            triggerBuild = always()
            withPendingChangesOnly = false
            enableQueueOptimization = false
            param("revisionRuleBuildBranch", "<default>")

            enforceCleanCheckoutForDependencies = true
        }
    }

    dependencies {
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyRelease3AssemblePackages")) {
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
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = """
                    *.zip
                    -:web-console.zip
                """.trimIndent()
            }
        }
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleDockerImage")) {
            snapshot {
                onDependencyFailure = FailureAction.IGNORE
                onDependencyCancel = FailureAction.IGNORE
            }

            artifacts {
                cleanDestination = true
                artifactRules = "*.tar.gz"
            }
        }
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleNugetPackages")) {
            snapshot {
                onDependencyFailure = FailureAction.IGNORE
            }

            artifacts {
                cleanDestination = true
                artifactRules = "apache-ignite-%IGNITE_VERSION%-nuget-staging.zip"
            }
        }
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare")) {
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

