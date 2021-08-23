package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BinaryObjectsSimpleMapperComputeGrid : BuildType({
    templates(RunTestSuitesJava)
    name = "Binary Objects (Simple Mapper Compute Grid)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinaryObjectsSimpleNameMapperComputeGridTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
