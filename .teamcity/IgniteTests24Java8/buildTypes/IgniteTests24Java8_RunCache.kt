package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_RunCache : BuildType({
    name = "-> Run :: Cache"
    description = "Dummy build for run all build in project by one click"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(IgniteTests24Java8_Cache1) {
        }
        snapshot(IgniteTests24Java8_Cache2) {
        }
        snapshot(IgniteTests24Java8_Cache3) {
        }
        snapshot(IgniteTests24Java8_Cache4) {
        }
        snapshot(IgniteTests24Java8_Cache5) {
        }
        snapshot(IgniteTests24Java8_Cache6) {
        }
        snapshot(IgniteTests24Java8_Cache7) {
        }
        snapshot(IgniteTests24Java8_Cache8) {
        }
        snapshot(IgniteTests24Java8_Cache9) {
        }
        snapshot(IgniteTests24Java8_CacheDeadlockDetection) {
        }
        snapshot(IgniteTests24Java8_CacheExpiryPolicy) {
        }
        snapshot(IgniteTests24Java8_CacheFailover1) {
        }
        snapshot(IgniteTests24Java8_CacheFailover2) {
        }
        snapshot(IgniteTests24Java8_CacheFailover3) {
        }
        snapshot(IgniteTests24Java8_CacheFailoverSsl) {
        }
        snapshot(IgniteTests24Java8_CacheFullApi) {
        }
        snapshot(IgniteTests24Java8_CacheFullApiBasicConfigVariations) {
        }
        snapshot(IgniteTests24Java8_CacheFullApiConfigVariations) {
        }
        snapshot(IgniteTests24Java8_CacheFullApiMultiJvm) {
        }
        snapshot(IgniteTests24Java8_CacheRestarts1) {
        }
        snapshot(IgniteTests24Java8_CacheRestarts2) {
        }
        snapshot(IgniteTests24Java8_CacheTxRecovery) {
        }
        snapshot(IgniteTests24Java8_DataStructures) {
        }
        snapshot(IgniteTests24Java8_JCacheTck11) {
        }
        snapshot(IgniteTests24Java8_Javadoc) {
        }
        snapshot(IgniteTests24Java8_LicensesHeaders) {
        }
        snapshot(IgniteTests24Java8_Queries1) {
        }
    }
})
