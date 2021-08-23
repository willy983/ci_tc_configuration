package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFailover3 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Failover) 3"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuite3")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
