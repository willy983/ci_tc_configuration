package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PdsDirectIo1 : BuildType({
    templates(RunTestSuitesJava)
    name = "PDS (Direct IO) 1"

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
