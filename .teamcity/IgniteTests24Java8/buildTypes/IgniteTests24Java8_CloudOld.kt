package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CloudOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Cloud"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("env.test.google-compute-engine.zones.list", "us-central1-a, asia-east1-a")
        param("MAVEN_MODULES", ":ignite-cloud")
        password("env.test.aws-ec2.secret.key", "zxx1a85e153a1b13eb8206b85e5c8f0f9f6f2507415a2c1439bc7072b419840ec2d91d0716164af1dd0775d03cbe80d301b")
        param("env.test.aws-ec2.regions.list", "us-east-1")
        param("TEST_SUITE", "IgniteCloudTestSuite")
        password("env.test.google-compute-engine.access.key", "zxx8032db6908839550a613426192d62c666d5761588218f21017c6408c18633819e056390e25997af83e9dca01fa26254aa601caa1b55a9f121c0a5e495bf60d561cc582af16ca0d12895d174a13cb74b6")
        password("env.test.rackspace-cloudservers-us.access.key", "zxx3697a8bc5c8df10c4469c7c25073dd9f")
        password("env.test.aws-ec2.access.key", "zxxedeb229a3321fa1c1733e75fead3158f012eb8bfec8d0b2d")
        param("env.test.google-compute-engine.secret.key", "/home/teamcity/test_keys/gridgain-gce-key.json")
        password("env.test.rackspace-cloudservers-us.secret.key", "zxxa17d5b262d6db2bb427283fc71fc16a8cc57d4b39a0b5ae3a967d41a60926f46775d03cbe80d301b")
        param("env.test.aws-ec2.zones.list", "us-east-1b, us-east-1e")
        param("env.test.rackspace-cloudservers-us.regions.list", "IAD,HKG")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
