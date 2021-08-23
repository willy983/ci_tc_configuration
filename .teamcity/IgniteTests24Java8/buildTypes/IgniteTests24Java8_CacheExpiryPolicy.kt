package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheExpiryPolicy : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Expiry Policy)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheExpiryPolicyTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
