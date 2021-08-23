package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ExamplesLgpl : BuildType({
    templates(RunTestSuitesJava)
    name = "Examples (LGPL)"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_OPTS", "-Plgpl")
        param("MAVEN_MODULES", ":ignite-examples")
        param("TEST_SUITE", "IgniteLgplExamplesSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 15
    }
})
