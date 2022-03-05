package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object IgniteExtensions_Tests_RunAllTests : BuildType({
    name = "--> Run :: All Tests"

    type = BuildTypeSettings.Type.COMPOSITE

    params {
        text("reverse.dep.*.OVERRIDDEN_BRANCH", "master", label = "Branch", description = "Overridden branch for Apache Ignite repository", allowEmpty = false)
    }

    vcs {
        root(AbsoluteId("GitHubApacheIgniteExtensions"))

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(IgniteExtensions_Tests_Aws_2) {
        }
        snapshot(IgniteExtensions_Tests_Camel) {
        }
        snapshot(IgniteExtensions_Tests_Cdc) {
        }
        snapshot(IgniteExtensions_Tests_Flink) {
        }
        snapshot(IgniteExtensions_Tests_Flume) {
        }
        snapshot(IgniteExtensions_Tests_Jms11) {
        }
        snapshot(IgniteExtensions_Tests_Kafka) {
        }
        snapshot(IgniteExtensions_Tests_Mqtt) {
        }
        snapshot(IgniteExtensions_Tests_PerformanceStatistics) {
        }
        snapshot(IgniteExtensions_Tests_PubSub) {
        }
        snapshot(IgniteExtensions_Tests_Rocketmq) {
        }
        snapshot(IgniteExtensions_Tests_SpringBootAutoconfigure) {
        }
        snapshot(IgniteExtensions_Tests_SpringBootThinClientAutoconfigure) {
        }
        snapshot(IgniteExtensions_Tests_SpringCache) {
        }
        snapshot(IgniteExtensions_Tests_SpringData) {
        }
        snapshot(IgniteExtensions_Tests_SpringData20) {
        }
        snapshot(IgniteExtensions_Tests_SpringData22) {
        }
        snapshot(IgniteExtensions_Tests_SpringSession) {
        }
        snapshot(IgniteExtensions_Tests_SpringTransactions) {
        }
        snapshot(IgniteExtensions_Tests_Storm) {
        }
        snapshot(IgniteExtensions_Tests_TopologyValidator) {
        }
        snapshot(IgniteExtensions_Tests_Twitter) {
        }
        snapshot(IgniteExtensions_Tests_ZeroMQ) {
        }
        snapshot(IgniteExtensions_Tests_ZookeeperIpFinder) {
        }
    }
})
