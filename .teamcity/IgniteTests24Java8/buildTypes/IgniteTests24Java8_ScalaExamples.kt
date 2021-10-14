package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_ScalaExamples : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Scala (Examples)"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-scalar,:ignite-examples")
        param("TEST_SUITE", "ScalarSelfTestSuite,ScalarExamplesSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 20
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_45")
    }
})