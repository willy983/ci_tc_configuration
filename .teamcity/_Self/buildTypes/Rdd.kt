package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Rdd : BuildType({
    templates(RunTestSuitesJava)
    name = "RDD"
    description = "Requires 'test' maven goal for correct passing"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_GOALS", "surefire:test")
        param("MAVEN_MODULES", ":ignite-spark")
        param("TEST_SUITE", "IgniteRDDTestSuite,IgniteDataFrameSuite")
    }

    failureConditions {
        executionTimeoutMin = 140
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
