package ignite2_Release_ApacheIgniteMain

import ignite2_Release_ApacheIgniteMain.buildTypes.*
import ignite2_Release_ApacheIgniteMain.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ignite2_Release_ApacheIgniteMain")
    name = "[Main]"

    vcsRoot(ignite2_Release_ApacheIgniteMain_GitBoxIgnite)

    buildType(ignite2_Release_ApacheIgniteReleaseJava8_PrepareVote4CheckRcLicensesChecksum)
    buildType(ignite2_Release_ApacheIgniteReleaseJava8_IgniteRelease72CheckFileConsistency)
    buildType(ignite2_Release_ApacheIgniteMain_ReleaseBuild_1)
    buildType(ignite2_Release_ApacheIgniteReleaseJava8_PrepareVote3BuildNuGetPackages)

    subProject(ignite2_Release_ApacheIgniteMain_ReleaseBuild.Project)
})
