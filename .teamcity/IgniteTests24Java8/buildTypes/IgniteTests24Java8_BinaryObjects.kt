package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BinaryObjects : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Binary Objects"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinaryObjectsTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
