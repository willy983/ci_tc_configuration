package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object WebSessions : BuildType({
    templates(RunTestSuitesJava)
    name = "Web Sessions"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-web")
        param("TEST_SUITE", "IgniteWebSessionSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 25
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
