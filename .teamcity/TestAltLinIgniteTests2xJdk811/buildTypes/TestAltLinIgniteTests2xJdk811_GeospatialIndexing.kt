package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_GeospatialIndexing : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Geospatial Indexing"

    params {
        text("MAVEN_MODULES", ":ignite-geospatial", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "GeoSpatialIndexingTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 15
    }
})
