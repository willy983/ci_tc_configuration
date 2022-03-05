package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Jta : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "JTA"

    params {
        text("MAVEN_MODULES", ":ignite-jta", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteJtaTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
