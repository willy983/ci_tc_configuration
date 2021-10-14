package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Pds3 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "PDS 3"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("env.IGNITE_WORK_DIR_ignore", "/data/teamcity/tmpfs/work")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "org.apache.ignite.testsuites.IgnitePdsTestSuite3")
    }

    failureConditions {
        executionTimeoutMin = 240
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})