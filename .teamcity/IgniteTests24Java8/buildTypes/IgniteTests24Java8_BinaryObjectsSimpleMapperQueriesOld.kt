package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BinaryObjectsSimpleMapperQueriesOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Queries (Binary Objects Simple Mapper)"

    params {
        param("MAVEN_MODULES", ":ignite-indexing")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheQueryTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
