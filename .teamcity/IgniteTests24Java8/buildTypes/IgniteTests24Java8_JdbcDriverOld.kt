package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_JdbcDriverOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] JDBC Driver"

    params {
        param("MAVEN_MODULES", ":ignite-clients")
        param("TEST_SUITE", "IgniteJdbcDriverTestSuite")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
