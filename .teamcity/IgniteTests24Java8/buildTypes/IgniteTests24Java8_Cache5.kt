package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cache5 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache 5"

    params {
        param("MAVEN_MODULES", ":ignite-core,:ignite-indexing")
        param("XMX", "4g")
        param("JVM_ARGS", """
            -XX:+UseG1GC
            -XX:+PerfDisableSharedMem
        """.trimIndent())
        param("TEST_SUITE", "IgniteCacheTestSuite5,IgniteCacheWithIndexingTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 180
    }
})
