package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunMvccCache : BuildType({
    name = "-> Run :: MVCC Cache"
    description = "Dummy build for run all build in project by one click"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(ExcludedMvccCache3) {
        }
        snapshot(MvccCache1) {
        }
        snapshot(MvccCache2) {
        }
        snapshot(MvccCache4) {
        }
        snapshot(MvccCache5) {
        }
        snapshot(MvccCache6) {
        }
        snapshot(MvccCache7) {
        }
        snapshot(MvccCache8) {
        }
        snapshot(MvccCache9) {
        }
        snapshot(MvccPds1) {
        }
        snapshot(MvccPds2) {
        }
        snapshot(MvccPds3) {
        }
        snapshot(MvccPds4) {
        }
    }
})
