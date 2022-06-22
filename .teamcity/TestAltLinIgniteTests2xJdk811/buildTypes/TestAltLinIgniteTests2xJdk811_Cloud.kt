package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Cloud : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Cloud"

    params {
        text("env.test.google-compute-engine.zones.list", "us-central1-a, asia-east1-a", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-cloud", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        password("env.test.aws-ec2.secret.key", "credentialsJSON:0e474af5-60f1-4d32-b5e6-50b36eabadb0")
        text("env.test.aws-ec2.regions.list", "us-east-1", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCloudTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        password("env.test.google-compute-engine.access.key", "credentialsJSON:587148fc-3c11-4304-bd97-f2178857ae02")
        password("env.test.rackspace-cloudservers-us.access.key", "credentialsJSON:50254a38-8b8c-4bf9-8cbf-1ad6442628fe")
        password("env.test.aws-ec2.access.key", "credentialsJSON:f257dfe3-c19a-4734-bb3e-aa638df329f8")
        text("env.test.google-compute-engine.secret.key", "/home/teamcity/test_keys/gridgain-gce-key.json", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        password("env.test.rackspace-cloudservers-us.secret.key", "credentialsJSON:e0c8959f-39ad-4cfb-a259-874cf8c43786")
        text("env.test.aws-ec2.zones.list", "us-east-1b, us-east-1e", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.test.rackspace-cloudservers-us.regions.list", "IAD,HKG", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
