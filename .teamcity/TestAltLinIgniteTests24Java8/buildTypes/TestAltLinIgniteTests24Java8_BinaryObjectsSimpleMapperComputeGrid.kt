package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_BinaryObjectsSimpleMapperComputeGrid : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Binary Objects (Simple Mapper Compute Grid)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinaryObjectsSimpleNameMapperComputeGridTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
