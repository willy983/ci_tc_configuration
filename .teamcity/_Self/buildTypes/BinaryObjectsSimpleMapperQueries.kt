package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BinaryObjectsSimpleMapperQueries : BuildType({
    templates(RunTestSuitesJava)
    name = "Queries (Binary Objects Simple Mapper)"

    params {
        param("MAVEN_MODULES", ":ignite-indexing")
        param("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheQueryTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
