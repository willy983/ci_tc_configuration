package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ControlUtilityZookeeper : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Control Utility (Zookeeper)"

    params {
        text("MAVEN_MODULES", ":ignite-control-utility", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ZookeeperIgniteControlUtilityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
