package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Security : BuildType({
    templates(RunTestSuitesJava)
    name = "Security"

    params {
        param("EXTRA_MAVEN_PROFILES", "-P surefire-fork-count-1")
        param("MAVEN_MODULES", ":ignite-core")
        param("env.IGNITE_TEST_FEATURES_ENABLED", "true")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "SecurityTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
