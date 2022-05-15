package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_SnapshotsWithIndexes : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Snapshots With Indexes"

    params {
        param("TEST_SCALE_FACTOR", "0.2")
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteSnapshotWithIndexingTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
