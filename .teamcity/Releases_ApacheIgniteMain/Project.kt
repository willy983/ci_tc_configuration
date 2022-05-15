package Releases_ApacheIgniteMain

import Releases_ApacheIgniteMain.buildTypes.*
import Releases_ApacheIgniteMain.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Releases_ApacheIgniteMain")
    name = "~[DEPRECATED] Apache Ignite / Main"

    vcsRoot(Releases_ApacheIgniteMain_GitBoxIgnite)

    buildType(Releases_ApacheIgniteMain_ReleaseBuild_1)
    buildType(ApacheIgniteRelease_Run_ApacheIgnitePostRelease_BuildUploadRpmDebPackages)
    buildType(ApacheIgniteReleaseJava8_PrepareVote)
    buildType(ApacheIgniteReleaseJava8_PrepareVote4CheckRcLicensesChecksum)
    buildType(ApacheIgniteReleaseJava8_IgniteRelease72CheckFileConsistency)
    buildType(ApacheIgniteReleaseJava8_PrepareVote3BuildNuGetPackages)
    buildType(ApacheIgniteReleaseJava8_TempIgniteRelease5GenerateReleaseReports)
    buildType(ApacheIgniteRelease_Run_ApacheIgnitePostRelease_BuildUploadDockerImages)

    subProject(Releases_ApacheIgniteMain_ReleaseBuild.Project)
})
