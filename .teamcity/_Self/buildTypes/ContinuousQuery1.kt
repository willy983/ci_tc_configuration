package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ContinuousQuery1 : BuildType({
    templates(RunTestSuitesJava)
    name = "Continuous Query 1"

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
        param("TEST_SUITE", "IgniteCacheQuerySelfTestSuite3")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
