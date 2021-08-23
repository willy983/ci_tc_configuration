package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BinaryObjects : BuildType({
    templates(RunTestSuitesJava)
    name = "Binary Objects"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinaryObjectsTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
