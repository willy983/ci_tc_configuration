package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object JdbcDriver : BuildType({
    templates(RunTestSuitesJava)
    name = "JDBC Driver"

    params {
        param("MAVEN_MODULES", ":ignite-clients")
        param("TEST_SUITE", "IgniteJdbcDriverTestSuite")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
