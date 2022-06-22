package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery3 : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "ZooKeeper (Discovery) 3"

    params {
        text("MAVEN_MODULES", ":ignite-zookeeper", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ZookeeperDiscoverySpiTestSuite3", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
