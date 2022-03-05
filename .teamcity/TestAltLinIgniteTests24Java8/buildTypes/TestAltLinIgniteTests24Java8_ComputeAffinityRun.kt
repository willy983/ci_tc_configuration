package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ComputeAffinityRun : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Compute (Affinity Run)"

    params {
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheAffinityRunTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
