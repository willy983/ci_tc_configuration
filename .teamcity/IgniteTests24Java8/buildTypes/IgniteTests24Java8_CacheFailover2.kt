package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFailover2 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Failover) 2"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuite2")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
