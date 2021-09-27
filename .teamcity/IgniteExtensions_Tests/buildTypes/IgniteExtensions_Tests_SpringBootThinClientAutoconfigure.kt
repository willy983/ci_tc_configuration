package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_SpringBootThinClientAutoconfigure : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Spring Boot Thin Client Autoconfigure"

    params {
        text("DIR_EXTENSION", "spring-boot-thin-client-autoconfigure-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
