package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cache9 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache 9"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTestSuite9")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
