package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cache1 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache 1"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinaryCacheTestSuite,IgniteRestHandlerTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
