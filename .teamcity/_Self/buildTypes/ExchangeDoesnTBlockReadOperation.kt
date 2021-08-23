package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ExchangeDoesnTBlockReadOperation : BuildType({
    templates(RunTestSuitesJava)
    name = "Exchange doesn't block read operation"

    params {
        param("MAVEN_MODULES", ":ignite-core,:ignite-indexing")
        param("TEST_SUITE", "IgniteCacheBlockExchangeOnReadOperationsTestSuite,IgniteCacheBlockExchangeOnSqlReadOperationsTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
