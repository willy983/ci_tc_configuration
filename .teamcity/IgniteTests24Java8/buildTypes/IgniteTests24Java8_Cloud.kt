package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Cloud : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cloud"

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
        password("env.test.aws-ec2.secret.key", "credentialsJSON:0e474af5-60f1-4d32-b5e6-50b36eabadb0")
        param("env.test.aws-ec2.regions.list", "us-east-1")
        param("TEST_SUITE", "IgniteCloudTestSuite")
        password("env.test.google-compute-engine.access.key", "credentialsJSON:587148fc-3c11-4304-bd97-f2178857ae02")
        password("env.test.rackspace-cloudservers-us.access.key", "credentialsJSON:50254a38-8b8c-4bf9-8cbf-1ad6442628fe")
        password("env.test.aws-ec2.access.key", "credentialsJSON:f257dfe3-c19a-4734-bb3e-aa638df329f8")
        param("env.test.google-compute-engine.secret.key", "/home/teamcity/test_keys/gridgain-gce-key.json")
        password("env.test.rackspace-cloudservers-us.secret.key", "credentialsJSON:e0c8959f-39ad-4cfb-a259-874cf8c43786")
        param("env.test.aws-ec2.zones.list", "us-east-1b, us-east-1e")
        param("env.test.rackspace-cloudservers-us.regions.list", "IAD,HKG")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
