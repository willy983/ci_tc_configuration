package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SpiWindows : BuildType({
    templates(RunTestSuitesJava)
    name = "SPI [Windows]"

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
        param("TEST_SUITE", "IgniteSpiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 100
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Windows 10", "RQ_10")
    }
})
