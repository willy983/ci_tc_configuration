package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object IgniteTests24Java8_RunAllPds : BuildType({
    name = "-> Run :: PDS"
    description = "Dummy build for run all build in project by one click"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

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
        snapshot(IgniteTests24Java8_Pds1) {
        }
        snapshot(IgniteTests24Java8_Pds2) {
        }
        snapshot(IgniteTests24Java8_Pds3) {
        }
        snapshot(IgniteTests24Java8_Pds4) {
        }
        snapshot(IgniteTests24Java8_PdsCompatibility) {
        }
        snapshot(IgniteTests24Java8_PdsDirectIo1) {
        }
        snapshot(IgniteTests24Java8_PdsDirectIo2) {
        }
        snapshot(IgniteTests24Java8_PdsIndexing) {
        }
        snapshot(IgniteTests24Java8_PdsUnitTests) {
        }
    }
})
