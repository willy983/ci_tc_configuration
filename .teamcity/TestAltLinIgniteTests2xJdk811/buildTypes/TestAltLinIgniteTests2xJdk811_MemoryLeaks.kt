package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_MemoryLeaks : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Memory Leaks"

    params {
        text("MAVEN_MODULES", ":ignite-core,:ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteDbMemoryLeakTestSuite,IgniteDbMemoryLeakWithIndexingTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
