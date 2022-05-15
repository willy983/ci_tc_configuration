package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Rdd : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "RDD"

    params {
        text("MAVEN_MODULES", ":ignite-spark", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteRDDTestSuite,IgniteDataFrameSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 140
    }
})
