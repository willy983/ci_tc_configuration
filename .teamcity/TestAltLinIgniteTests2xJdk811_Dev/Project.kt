package TestAltLinIgniteTests2xJdk811_Dev

import TestAltLinIgniteTests2xJdk811_Dev.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection

object Project : Project({
    id("TestAltLinIgniteTests2xJdk811_Dev")
    name = "[DEV]"

    buildType(TestAltLinIgniteTests2xJdk811_Dev_SqlTest)

    features {
        githubConnection {
            id = "PROJECT_EXT_11"
            displayName = "GitHub.com"
            clientId = "willy983"
            clientSecret = "credentialsJSON:8772fb5d-fc57-4621-8952-afa52a396384"
        }
    }
})
