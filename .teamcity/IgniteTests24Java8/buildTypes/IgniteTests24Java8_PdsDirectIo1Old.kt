package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_PdsDirectIo1Old : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] PDS (Direct IO) 1"

    params {
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("MAVEN_MODULES", ":ignite-direct-io")
        param("TEST_SUITE", "IgnitePdsNativeIoTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})
