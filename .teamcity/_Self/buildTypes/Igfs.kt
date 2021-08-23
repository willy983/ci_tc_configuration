package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Igfs : BuildType({
    templates(RunTestSuitesJava)
    name = "IGFS"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteIgfsTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
