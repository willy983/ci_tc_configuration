package IgniteTests24Java8_Dev

import IgniteTests24Java8_Dev.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection

object Project : Project({
    id("IgniteTests24Java8_Dev")
    name = "[DEV]"

    buildType(IgniteTests24Java8_Dev_SqlTest)
    buildType(IgniteTests24Java8_Dev_Test1)

    features {
        githubConnection {
            id = "PROJECT_EXT_6"
            displayName = "GitHub.com"
            clientId = "willy983"
            clientSecret = "credentialsJSON:8772fb5d-fc57-4621-8952-afa52a396384"
        }
    }
})
