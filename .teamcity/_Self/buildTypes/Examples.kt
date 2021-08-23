package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Examples : BuildType({
    templates(RunTestSuitesJava)
    name = "Examples"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("JVM_ARGS", """
            -Djava.awt.headless=true
            -Dawt.toolkit=sun.awt.HToolkit
        """.trimIndent())
        param("MAVEN_MODULES", ":ignite-examples")
        param("TEST_SUITE", "IgniteExamplesSelfTestSuite,IgniteExamplesSparkSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
