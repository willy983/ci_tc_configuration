package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DiskPageCompressions : BuildType({
    templates(RunTestSuitesJava)
    name = "Disk Page Compressions"

    params {
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("MAVEN_MODULES", ":ignite-compress")
        param("TEST_SUITE", "IgnitePdsCompressionTestSuite,IgnitePdsCompressionTestSuite2")
    }

    failureConditions {
        executionTimeoutMin = 240
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})
