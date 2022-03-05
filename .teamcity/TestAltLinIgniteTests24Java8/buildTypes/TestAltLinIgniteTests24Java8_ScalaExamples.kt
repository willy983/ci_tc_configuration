package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ScalaExamples : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Scala (Examples)"

    params {
        param("EXTRA_MAVEN_PROFILES", "-P scala-test")
        text("MAVEN_MODULES", ":ignite-scalar,:ignite-examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ScalarSelfTestSuite,ScalarExamplesSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 140
    }
})
