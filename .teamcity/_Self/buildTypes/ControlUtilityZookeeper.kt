package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ControlUtilityZookeeper : BuildType({
    templates(RunTestSuitesJava)
    name = "Control Utility (Zookeeper)"

    params {
        text("MAVEN_MODULES", ":ignite-control-utility", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ZookeeperIgniteControlUtilityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
