package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BinaryObjectsSimpleMapperBasicOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Binary Objects (Simple Mapper Basic)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperBasicTestSuite")
        param("env.IGNITE_TEST_FEATURES_ENABLED", "true")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
