package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Aop : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "AOP"

    params {
        param("MAVEN_MODULES", ":ignite-aop")
        param("TEST_SUITE", "IgniteAopSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
