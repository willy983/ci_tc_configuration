package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_WebSessions : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Web Sessions"

    params {
        text("MAVEN_MODULES", ":ignite-web", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteWebSessionSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
