package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunCache : BuildType({
    name = "-> Run :: Cache"
    description = "Dummy build for run all build in project by one click"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Cache1) {
        }
        snapshot(Cache2) {
        }
        snapshot(Cache3) {
        }
        snapshot(Cache4) {
        }
        snapshot(Cache5) {
        }
        snapshot(Cache6) {
        }
        snapshot(Cache7) {
        }
        snapshot(Cache8) {
        }
        snapshot(Cache9) {
        }
        snapshot(CacheDeadlockDetection) {
        }
        snapshot(CacheExpiryPolicy) {
        }
        snapshot(CacheFailover1) {
        }
        snapshot(CacheFailover2) {
        }
        snapshot(CacheFailover3) {
        }
        snapshot(CacheFailoverSsl) {
        }
        snapshot(CacheFullApi) {
        }
        snapshot(CacheFullApiBasicConfigVariations) {
        }
        snapshot(CacheFullApiConfigVariations) {
        }
        snapshot(CacheFullApiMultiJvm) {
        }
        snapshot(CacheRestarts1) {
        }
        snapshot(CacheRestarts2) {
        }
        snapshot(CacheTxRecovery) {
        }
        snapshot(DataStructures) {
        }
        snapshot(JCacheTck11) {
        }
        snapshot(Javadoc) {
        }
        snapshot(LicensesHeaders) {
        }
        snapshot(Queries1) {
        }
    }
})
