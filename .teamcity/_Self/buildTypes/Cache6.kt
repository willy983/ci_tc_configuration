package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Cache6 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache 6"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("XMX", "10g")
        param("JVM_ARGS", """
            -XX:+UseG1GC
            -XX:+PerfDisableSharedMem
        """.trimIndent())
        param("TEST_SUITE", "IgniteCacheTestSuite6")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
