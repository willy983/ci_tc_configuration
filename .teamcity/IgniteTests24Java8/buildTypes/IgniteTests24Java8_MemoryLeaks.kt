package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_MemoryLeaks : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
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
