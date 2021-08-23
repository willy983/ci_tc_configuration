package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ControlUtility : BuildType({
    templates(RunTestSuitesJava)
    name = "Control Utility"

    params {
        text("MAVEN_MODULES", ":ignite-control-utility", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteControlUtilityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
