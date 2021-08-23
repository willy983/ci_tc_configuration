package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Aop : BuildType({
    templates(RunTestSuitesJava)
    name = "AOP"

    params {
        param("MAVEN_MODULES", ":ignite-aop")
        param("TEST_SUITE", "IgniteAopSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
