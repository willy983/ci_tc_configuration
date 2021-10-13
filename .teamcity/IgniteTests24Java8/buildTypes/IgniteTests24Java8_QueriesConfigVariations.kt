package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_QueriesConfigVariations : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Queries (Config Variations)"

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
        param("TEST_SUITE", "IgniteCacheConfigVariationQueryTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
