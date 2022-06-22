package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_CacheDeadlockDetection : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Cache (Deadlock Detection)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "TxDeadlockDetectionTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
