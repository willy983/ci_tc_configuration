package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ContinuousQuery2 : BuildType({
    templates(RunTestSuitesJava)
    name = "Continuous Query 2"

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
        param("TEST_SUITE", "IgniteCacheQuerySelfTestSuite4")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
