package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_DevUtils : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Dev Utils"

    params {
        text("MAVEN_MODULES", ":ignite-dev-utils", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "DevUtilsTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
