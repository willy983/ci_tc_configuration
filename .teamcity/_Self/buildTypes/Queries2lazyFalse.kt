package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Queries2lazyFalse : BuildType({
    templates(RunTestSuitesJava)
    name = "Queries 2 (lazy=true)"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("system.IGNITE_QUERY_LAZY_DEFAULT", "true")
        param("MAVEN_MODULES", ":ignite-indexing")
        param("TEST_SUITE", "IgniteBinaryCacheQueryTestSuite2 ")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
