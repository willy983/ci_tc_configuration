package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_ScalaExamples : BuildType({
    templates(IgniteTests24Java8_RunTestsJava)
    name = "Scala (Examples)"

    params {
        text("TEST_SUITE", "ScalarSelfTestSuite,ScalarExamplesSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-scalar,:ignite-examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("EXTRA_MAVEN_PROFILES", "-P scala-test")
    }

    failureConditions {
        executionTimeoutMin = 140
    }
})
