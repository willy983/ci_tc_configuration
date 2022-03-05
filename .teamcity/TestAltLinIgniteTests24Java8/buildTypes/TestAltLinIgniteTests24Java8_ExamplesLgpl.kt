package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ExamplesLgpl : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Examples (LGPL)"

    params {
        param("MAVEN_OPTS", "-Plgpl")
        text("MAVEN_MODULES", ":ignite-examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteLgplExamplesSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 15
    }
})
