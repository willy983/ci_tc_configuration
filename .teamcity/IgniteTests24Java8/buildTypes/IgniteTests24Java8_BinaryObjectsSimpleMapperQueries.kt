package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_BinaryObjectsSimpleMapperQueries : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Queries (Binary Objects Simple Mapper)"

    params {
        param("MAVEN_MODULES", ":ignite-indexing")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheQueryTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
