package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFailover2 : BuildType({
    templates(IgniteTests24Java8_RunTestsJava)
    name = "Cache (Failover) 2"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheFailoverTestSuite2", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    cleanup {
        baseRule {
            all(days = 15)
            history(days = 15)
            artifacts(days = 15, artifactPatterns = """
                +:**/*
                +:.teamcity/**
            """.trimIndent())
        }
    }
})
