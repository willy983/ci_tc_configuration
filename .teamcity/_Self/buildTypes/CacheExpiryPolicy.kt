package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheExpiryPolicy : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Expiry Policy)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheExpiryPolicyTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
