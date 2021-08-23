package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFailover1 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Failover) 1"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
