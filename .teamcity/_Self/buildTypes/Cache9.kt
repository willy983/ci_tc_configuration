package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Cache9 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache 9"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTestSuite9")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
