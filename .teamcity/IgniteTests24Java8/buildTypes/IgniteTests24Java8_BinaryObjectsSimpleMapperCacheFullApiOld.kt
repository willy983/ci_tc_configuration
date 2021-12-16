package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BinaryObjectsSimpleMapperCacheFullApiOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Binary Objects (Simple Mapper Cache Full API)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
