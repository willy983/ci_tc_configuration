package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Ml : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "ML"

    params {
        text("MAVEN_MODULES", ":ignite-ml", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteMLTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
