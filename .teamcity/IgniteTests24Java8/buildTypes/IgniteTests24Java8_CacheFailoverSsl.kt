package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFailoverSsl : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Failover SSL)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFailoverTestSuiteSsl")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
