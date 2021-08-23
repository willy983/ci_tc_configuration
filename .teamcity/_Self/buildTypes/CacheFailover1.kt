package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFailover1 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Failover) 1"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
