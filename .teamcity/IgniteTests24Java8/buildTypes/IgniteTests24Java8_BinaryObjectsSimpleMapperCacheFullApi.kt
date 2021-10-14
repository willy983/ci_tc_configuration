package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BinaryObjectsSimpleMapperCacheFullApi : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Binary Objects (Simple Mapper Cache Full API)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})