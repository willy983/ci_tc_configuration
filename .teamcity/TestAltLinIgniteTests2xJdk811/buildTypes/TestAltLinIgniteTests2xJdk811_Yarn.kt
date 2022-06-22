package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Yarn : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Yarn"

    params {
        text("MAVEN_MODULES", ":ignite-yarn", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteYarnTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
