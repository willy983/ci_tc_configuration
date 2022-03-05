package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'TestAltLinIgniteTests2xJdk811_StartNodes'
in the project with id = 'TestAltLinIgniteTests2xJdk811', and delete the patch script.
*/
create(RelativeId("TestAltLinIgniteTests2xJdk811"), BuildType({
    templates(RelativeId("TestAltLinIgniteTests2xJdk811_RunTestsJava"), RelativeId("TestAltLinIgniteTests2xJdk811_RunNodeLibs"))
    id("TestAltLinIgniteTests2xJdk811_StartNodes")
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
}))

