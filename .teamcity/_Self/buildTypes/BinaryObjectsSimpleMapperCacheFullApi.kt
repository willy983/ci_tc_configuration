package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BinaryObjectsSimpleMapperCacheFullApi : BuildType({
    templates(RunTestSuitesJava)
    name = "Binary Objects (Simple Mapper Cache Full API)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
