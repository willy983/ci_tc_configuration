package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ClientNodes : BuildType({
    templates(RunTestSuitesJava)
    name = "Client Nodes"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("XMX", "4g")
        param("TEST_SUITE", "IgniteClientNodesTestSuite,org.apache.ignite.testsuites.IgniteClientReconnectTestSuite,org.apache.ignite.internal.processors.rest.protocols.http.jetty.GridJettySuite")
        param("XMS", "4g")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
