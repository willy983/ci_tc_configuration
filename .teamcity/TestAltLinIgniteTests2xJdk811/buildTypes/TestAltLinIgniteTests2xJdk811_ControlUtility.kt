package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ControlUtility : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Control Utility"

    params {
        text("MAVEN_MODULES", ":ignite-control-utility", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteControlUtilityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
