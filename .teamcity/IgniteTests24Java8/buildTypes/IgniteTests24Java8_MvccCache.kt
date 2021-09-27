package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_MvccCache : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
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
