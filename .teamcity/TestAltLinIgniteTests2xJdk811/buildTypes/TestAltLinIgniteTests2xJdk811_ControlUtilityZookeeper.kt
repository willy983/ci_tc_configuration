package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ControlUtilityZookeeper : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Control Utility (Zookeeper)"

    params {
        text("MAVEN_MODULES", ":ignite-control-utility", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ZookeeperIgniteControlUtilityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
