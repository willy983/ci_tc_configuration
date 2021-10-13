package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Hibernate2 : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Hibernate 5.1"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-hibernate_5.1")
        param("TEST_SUITE", "IgniteHibernate5TestSuite")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
