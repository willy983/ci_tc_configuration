package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object GeospatialIndexing : BuildType({
    templates(RunTestSuitesJava)
    name = "Geospatial Indexing"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-geospatial")
        param("TEST_SUITE", "GeoSpatialIndexingTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 15
    }
})
