package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_ActivateDeactivateClusterOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Activate / Deactivate Cluster"

    params {
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteStandByClusterSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        doesNotMatch("teamcity.agent.name", "(^publicagent05.*${'$'})", "RQ_72")
    }
})
