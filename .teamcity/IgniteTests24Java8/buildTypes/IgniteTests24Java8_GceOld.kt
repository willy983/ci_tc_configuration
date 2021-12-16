package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_GceOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] GCE"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-gce")
        param("env.test.gce.project.name", "449058130467")
        password("env.test.gce.account.id", "zxx8032db6908839550a613426192d62c666d5761588218f21017c6408c18633819e056390e25997af83e9dca01fa26254aa601caa1b55a9f121c0a5e495bf60d561cc582af16ca0d12895d174a13cb74b6")
        param("env.test.gce.p12.path", "/root/.gce/gridgain-gce-key.p12")
        param("TEST_SUITE", "IgniteGCETestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
