package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_ExchangeDoesnTBlockReadOperation : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Exchange doesn't block read operation"

    params {
        param("MAVEN_MODULES", ":ignite-core,:ignite-indexing")
        param("TEST_SUITE", "IgniteCacheBlockExchangeOnReadOperationsTestSuite,IgniteCacheBlockExchangeOnSqlReadOperationsTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
