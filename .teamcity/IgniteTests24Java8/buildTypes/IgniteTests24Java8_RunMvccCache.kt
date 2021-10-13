package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_RunMvccCache : BuildType({
    name = "-> Run :: MVCC Cache"
    description = "Dummy build for run all build in project by one click"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(IgniteTests24Java8_ExcludedMvccCache3) {
        }
        snapshot(IgniteTests24Java8_MvccCache1) {
        }
        snapshot(IgniteTests24Java8_MvccCache2) {
        }
        snapshot(IgniteTests24Java8_MvccCache4) {
        }
        snapshot(IgniteTests24Java8_MvccCache5) {
        }
        snapshot(IgniteTests24Java8_MvccCache6) {
        }
        snapshot(IgniteTests24Java8_MvccCache7) {
        }
        snapshot(IgniteTests24Java8_MvccCache8) {
        }
        snapshot(IgniteTests24Java8_MvccCache9) {
        }
        snapshot(IgniteTests24Java8_MvccPds1) {
        }
        snapshot(IgniteTests24Java8_MvccPds2) {
        }
        snapshot(IgniteTests24Java8_MvccPds3) {
        }
        snapshot(IgniteTests24Java8_MvccPds4) {
        }
    }
})
