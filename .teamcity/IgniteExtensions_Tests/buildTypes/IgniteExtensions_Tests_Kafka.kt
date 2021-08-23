package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_Kafka : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Kafka"

    params {
        text("DIR_EXTENSION", "kafka-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
