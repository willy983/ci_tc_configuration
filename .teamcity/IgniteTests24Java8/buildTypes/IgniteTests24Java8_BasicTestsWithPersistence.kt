package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BasicTestsWithPersistence : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Basic 3"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("env.IGNITE_TEST_FEATURES_ENABLED", "true")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "org.apache.ignite.testsuites.IgniteBasicWithPersistenceTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
