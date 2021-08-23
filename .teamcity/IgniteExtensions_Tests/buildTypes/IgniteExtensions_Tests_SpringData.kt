package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_SpringData : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Spring Data"

    params {
        text("DIR_EXTENSION", "spring-data-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
