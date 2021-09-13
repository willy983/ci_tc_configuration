package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_SpringData20 : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Spring Data 2.0"

    params {
        text("DIR_EXTENSION", "spring-data-2.0-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
