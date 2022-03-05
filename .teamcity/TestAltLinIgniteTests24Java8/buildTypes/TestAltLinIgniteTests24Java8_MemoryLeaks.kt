package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_MemoryLeaks : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Memory Leaks"

    params {
        text("MAVEN_MODULES", ":ignite-core,:ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteDbMemoryLeakTestSuite,IgniteDbMemoryLeakWithIndexingTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
