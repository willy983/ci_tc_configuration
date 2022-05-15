package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object CiTcConfiguration : GitVcsRoot({
    name = "GitHub (CI TC Configuration)"
    url = "https://github.com/willy983/ci_tc_configuration.git"
    pushUrl = "https://github.com/willy983/ci_tc_configuration.git"
    branch = "master"
    branchSpec = """
        +:refs/heads/(master)
        +:refs/(pull/*)/head
    """.trimIndent()
    authMethod = password {
        userName = "willy983"
        password = "credentialsJSON:edc86dc6-dd3e-4d97-bc55-e920fc6597fc"
    }
})
