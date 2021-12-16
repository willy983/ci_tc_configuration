package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Basic1Old : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Basic 1"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("env.IGNITE_TEST_FEATURES_ENABLED", "true")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "IgniteBasicTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
