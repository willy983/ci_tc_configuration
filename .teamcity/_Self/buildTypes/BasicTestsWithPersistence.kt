package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BasicTestsWithPersistence : BuildType({
    templates(RunTestSuitesJava)
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
