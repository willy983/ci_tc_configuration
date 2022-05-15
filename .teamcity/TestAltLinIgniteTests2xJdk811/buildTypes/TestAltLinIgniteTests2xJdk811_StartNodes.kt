package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_StartNodes : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava, TestAltLinIgniteTests2xJdk811_RunNodeLibs)
    name = "Start Nodes"

    params {
        text("MAVEN_MODULES", ":ignite-ssh", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.test.ssh.password", "teamcity", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteStartStopRestartTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.test.ssh.username", "teamcity", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
