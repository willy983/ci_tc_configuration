package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object MemoryLeaks : BuildType({
    templates(RunTestSuitesJava)
    name = "Memory Leaks"

    params {
        param("MAVEN_OPTS", "")
        param("MAVEN_MODULES", ":ignite-core,:ignite-indexing")
        param("TEST_SUITE", "IgniteDbMemoryLeakTestSuite,IgniteDbMemoryLeakWithIndexingTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
