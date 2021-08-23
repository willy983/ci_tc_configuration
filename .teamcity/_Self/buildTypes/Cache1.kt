package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Cache1 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache 1"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinaryCacheTestSuite,IgniteRestHandlerTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
