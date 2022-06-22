package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ExamplesLgpl : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Examples (LGPL)"

    params {
        text("MAVEN_OPTS", "-Plgpl", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteLgplExamplesSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 15
    }
})
