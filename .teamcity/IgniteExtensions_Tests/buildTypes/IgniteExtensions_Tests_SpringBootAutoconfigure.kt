package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_SpringBootAutoconfigure : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Spring Boot Autoconfigure"

    params {
        text("DIR_EXTENSION", "spring-boot-autoconfigure-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
