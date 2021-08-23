package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cache7 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache 7"

    params {
        param("MAVEN_MODULES", ":ignite-core,:ignite-indexing")
        param("TEST_SUITE", "org.apache.ignite.testsuites.IgniteCacheTestSuite7,org.apache.ignite.testsuites.IgniteCacheWithIndexingAndPersistenceTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
