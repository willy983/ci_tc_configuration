package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Pds2 : BuildType({
    templates(RunTestSuitesJava)
    name = "PDS 2"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("env.IGNITE_WORK_DIR_ignore", "/data/teamcity/tmpfs/work")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "IgnitePdsTestSuite2")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})
