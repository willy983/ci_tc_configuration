package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Rdd : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "RDD"

    params {
        text("MAVEN_MODULES", ":ignite-spark", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteRDDTestSuite,IgniteDataFrameSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 140
    }
})
