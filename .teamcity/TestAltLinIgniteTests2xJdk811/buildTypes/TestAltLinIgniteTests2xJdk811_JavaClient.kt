package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_JavaClient : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Java Client"

    params {
        text("MAVEN_MODULES", ":ignite-clients,:ignite-rest-http", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteClientTestSuite,GridRestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("XMX", "8g", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 50
    }
})
