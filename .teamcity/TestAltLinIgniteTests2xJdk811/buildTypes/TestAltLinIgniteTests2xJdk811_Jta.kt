package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Jta : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "JTA"

    params {
        text("MAVEN_MODULES", ":ignite-jta", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteJtaTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
