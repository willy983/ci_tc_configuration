package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object RunAllPds : BuildType({
    name = "-> Run :: PDS"
    description = "Dummy build for run all build in project by one click"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    triggers {
        vcs {
            enabled = false
            branchFilter = "+:refs/pull/548/head"
            watchChangesInDependencies = true
        }
        schedule {
            enabled = false
            schedulingPolicy = daily {
                hour = 4
            }
            branchFilter = "+:<default>"
            triggerBuild = always()
            withPendingChangesOnly = false
            param("revisionRuleBuildBranch", "<default>")

            enforceCleanCheckout = true
        }
        schedule {
            enabled = false
            schedulingPolicy = daily {
                hour = 6
            }
            branchFilter = "+:<default>"
            triggerBuild = always()
            withPendingChangesOnly = false
            param("revisionRuleBuildBranch", "<default>")

            enforceCleanCheckout = true
        }
    }

    dependencies {
        snapshot(Pds1) {
        }
        snapshot(Pds2) {
        }
        snapshot(Pds3) {
        }
        snapshot(Pds4) {
        }
        snapshot(PdsCompatibility) {
        }
        snapshot(PdsDirectIo1) {
        }
        snapshot(PdsDirectIo2) {
        }
        snapshot(PdsIndexing) {
        }
        snapshot(PdsUnitTests) {
        }
    }
})
