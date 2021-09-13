package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Jta : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "JTA"

    params {
        param("MAVEN_MODULES", ":ignite-jta")
        param("TEST_SUITE", "IgniteJtaTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
