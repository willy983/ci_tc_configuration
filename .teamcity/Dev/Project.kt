package Dev

import Dev.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection

object Project : Project({
    id("Dev")
    name = "[DEV]"

    buildType(Dev_SqlTest)

    features {
        githubConnection {
            id = "PROJECT_EXT_6"
            displayName = "GitHub.com"
            clientId = "willy983"
            clientSecret = "credentialsJSON:8772fb5d-fc57-4621-8952-afa52a396384"
        }
    }
})
