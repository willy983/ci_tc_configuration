package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PdsUnitTests : BuildType({
    templates(RunTestSuitesJava)
    name = "PDS (Unit Tests)"

    params {
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgnitePdsUnitTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})
