package ignite3_Test_IntegrationTests

import ignite3_Test_IntegrationTests.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ignite3_Test_IntegrationTests")
    name = "[Integration Tests]"

    buildType(ignite3_Test_IntegrationTests_IntegrationTests)
})
