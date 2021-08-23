package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ContinuousQueryConfigVariations : BuildType({
    templates(RunTestSuitesJava)
    name = "Continuous Query (Config Variations)"

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
        param("TEST_SUITE", "IgniteContinuousQueryConfigVariationsSuite")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
