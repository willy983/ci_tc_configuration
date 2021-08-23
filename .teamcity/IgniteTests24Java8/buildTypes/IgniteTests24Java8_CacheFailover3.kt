package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFailover3 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Failover) 3"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuite3")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
