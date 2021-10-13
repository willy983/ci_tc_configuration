package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_SpringTransactions : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Spring Transactions"

    params {
        param("DIR_EXTENSION", "spring-tx-ext")
    }
})
