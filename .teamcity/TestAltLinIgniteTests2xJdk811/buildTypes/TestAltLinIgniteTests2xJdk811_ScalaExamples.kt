package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ScalaExamples : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Scala (Examples)"

    params {
        text("EXTRA_MAVEN_PROFILES", "-P scala-test", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-scalar,:ignite-examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "ScalarSelfTestSuite,ScalarExamplesSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 140
    }
})
