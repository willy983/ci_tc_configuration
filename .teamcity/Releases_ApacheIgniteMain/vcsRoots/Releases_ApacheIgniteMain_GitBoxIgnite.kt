package Releases_ApacheIgniteMain.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Releases_ApacheIgniteMain_GitBoxIgnite : GitVcsRoot({
    name = "GitBox [ignite]"
    url = "https://gitbox.apache.org/repos/asf/ignite.git"
    branch = "master"
    branchSpec = """
        +:refs/heads/(ignite-*)
        +:refs/(pull/*/head)
    """.trimIndent()
    userNameStyle = GitVcsRoot.UserNameStyle.FULL
})

