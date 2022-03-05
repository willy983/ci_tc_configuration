package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Snapshots : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Snapshots"

    params {
        param("TEST_SCALE_FACTOR", "0.1")
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteSnapshotTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
