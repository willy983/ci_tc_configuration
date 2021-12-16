package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_AopOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] AOP"

    params {
        param("IGNITE_LOGGING_OPTS", "-DIGNITE_QUIET=true")
        param("MAVEN_MODULES", ":ignite-aop")
        param("TEST_SUITE", "IgniteAopSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
