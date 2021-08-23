package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cache2 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache 2"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTestSuite2")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 100
    }
})
