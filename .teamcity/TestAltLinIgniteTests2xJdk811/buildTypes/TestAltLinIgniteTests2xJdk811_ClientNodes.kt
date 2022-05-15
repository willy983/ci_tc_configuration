package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ClientNodes : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Client Nodes"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("XMX", "4g", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteClientNodesTestSuite,IgniteClientReconnectTestSuite,GridJettySuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("XMS", "4g", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
