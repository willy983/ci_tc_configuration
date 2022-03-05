package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ControlUtility : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Control Utility"

    params {
        text("MAVEN_MODULES", ":ignite-control-utility", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteControlUtilityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
