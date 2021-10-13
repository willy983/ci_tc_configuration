package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cache8 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache 8"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTestSuite8")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 210
    }
})
