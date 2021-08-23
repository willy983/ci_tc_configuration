package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object JavaThinClient : BuildType({
    templates(RunTestSuitesJava)
    name = "Thin Client: Java"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-indexing")
        param("TEST_SUITE", "ClientTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
