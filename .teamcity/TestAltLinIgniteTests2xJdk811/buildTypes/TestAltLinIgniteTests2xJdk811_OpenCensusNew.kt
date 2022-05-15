package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_OpenCensusNew : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Open Census"

    params {
        text("MAVEN_MODULES", ":ignite-opencensus", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteOpenCensusSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
