package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFailover2 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Failover) 2"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuite2")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
