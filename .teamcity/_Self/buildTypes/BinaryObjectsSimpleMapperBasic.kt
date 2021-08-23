package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BinaryObjectsSimpleMapperBasic : BuildType({
    templates(RunTestSuitesJava)
    name = "Binary Objects (Simple Mapper Basic)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperBasicTestSuite")
        param("env.IGNITE_TEST_FEATURES_ENABLED", "true")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
