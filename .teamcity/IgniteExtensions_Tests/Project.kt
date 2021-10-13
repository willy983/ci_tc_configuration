package IgniteExtensions_Tests

import IgniteExtensions_Tests.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("IgniteExtensions_Tests")
    name = "[TESTS]"

    buildType(IgniteExtensions_Tests_RunAllTests)
    buildType(IgniteExtensions_Tests_Camel)
    buildType(IgniteExtensions_Tests_PubSub)
    buildType(IgniteExtensions_Tests_Twitter)
    buildType(IgniteExtensions_Tests_SpringData22)
    buildType(IgniteExtensions_Tests_SpringCache)
    buildType(IgniteExtensions_Tests_SpringData20)
    buildType(IgniteExtensions_Tests_Flink)
    buildType(IgniteExtensions_Tests_Storm)
    buildType(IgniteExtensions_Tests_SpringSession)
    buildType(IgniteExtensions_Tests_Jms11)
    buildType(IgniteExtensions_Tests_Flume)
    buildType(IgniteExtensions_Tests_Kafka)
    buildType(IgniteExtensions_Tests_SpringBootThinClientAutoconfigure)
    buildType(IgniteExtensions_Tests_ZeroMQ)
    buildType(IgniteExtensions_Tests_OldRunAllTests)
    buildType(IgniteExtensions_Tests_Rocketmq)
    buildType(IgniteExtensions_Tests_SpringTransactions)
    buildType(IgniteExtensions_Tests_Cdc)
    buildType(IgniteExtensions_Tests_PerformanceStatistics)
    buildType(IgniteExtensions_Tests_SpringBootAutoconfigure)
    buildType(IgniteExtensions_Tests_SpringData)
    buildType(IgniteExtensions_Tests_Build)
    buildType(IgniteExtensions_Tests_Mqtt)

    template(IgniteExtensions_Tests_RunExtensionTests)
})
