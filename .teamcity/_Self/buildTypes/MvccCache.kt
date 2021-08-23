package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object MvccCache : BuildType({
    templates(RunTestSuitesJava)
    name = "MVCC JDBC"

    params {
        param("MAVEN_MODULES", ":ignite-clients")
        param("TEST_SUITE", "IgniteJdbcDriverMvccTestSuite")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
