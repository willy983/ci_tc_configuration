package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Cache4 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache 4"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTestSuite4")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
