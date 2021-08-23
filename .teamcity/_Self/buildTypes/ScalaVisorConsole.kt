package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ScalaVisorConsole : BuildType({
    templates(RunTestSuitesJava)
    name = "Scala (Visor Console)"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-visor-console")
        param("TEST_SUITE", "VisorConsoleSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 20
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})
