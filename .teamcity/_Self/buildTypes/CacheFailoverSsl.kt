package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFailoverSsl : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Failover SSL)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuiteSsl")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
