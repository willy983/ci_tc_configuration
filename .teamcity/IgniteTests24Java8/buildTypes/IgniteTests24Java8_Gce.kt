package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Gce : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "GCE"

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
        password("env.test.gce.account.id", "credentialsJSON:587148fc-3c11-4304-bd97-f2178857ae02")
        param("env.test.gce.p12.path", "/root/.gce/gridgain-gce-key.p12")
        param("TEST_SUITE", "IgniteGCETestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
