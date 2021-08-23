package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ZooKeeperDiscovery4 : BuildType({
    templates(RunTestSuitesJava)
    name = "ZooKeeper (Discovery) 4"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-zookeeper")
        param("TEST_SUITE", "ZookeeperDiscoverySpiTestSuite4")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
