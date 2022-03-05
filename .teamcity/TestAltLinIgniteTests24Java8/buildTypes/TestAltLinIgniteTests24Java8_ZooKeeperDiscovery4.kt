package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ZooKeeperDiscovery4 : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "ZooKeeper (Discovery) 4"

    params {
        text("MAVEN_MODULES", ":ignite-zookeeper", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ZookeeperDiscoverySpiTestSuite4", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
