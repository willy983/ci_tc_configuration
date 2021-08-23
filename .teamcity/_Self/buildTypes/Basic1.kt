package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Basic1 : BuildType({
    templates(RunTestSuitesJava)
    name = "Basic 1"

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
