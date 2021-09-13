package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Basic2 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Basic 2"

    params {
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("MAVEN_MODULES", ":ignite-schedule,:ignite-jcl,:ignite-log4j,:ignite-log4j2,:ignite-slf4j,:ignite-core")
        param("TEST_SUITE", "IgniteIpcTestSuite,IgniteSchedulerTestSuite,IgniteJclTestSuite,IgniteLog4jTestSuite,IgniteLog4j2TestSuite,IgniteSlf4jTestSuite,IgniteMessagingConfigVariationFullApiTestSuite,IgniteComputeBasicConfigVariationsFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
