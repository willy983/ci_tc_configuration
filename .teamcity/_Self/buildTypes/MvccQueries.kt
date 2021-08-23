package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object MvccQueries : BuildType({
    templates(RunTestSuitesJava)
    name = "MVCC Queries"

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
        param("TEST_SUITE", "IgniteCacheMvccSqlTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 150
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
