package Releases_ApacheIgniteMain_ReleaseBuild

import Releases_ApacheIgniteMain_ReleaseBuild.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

object Project : Project({
    id("Releases_ApacheIgniteMain_ReleaseBuild")
    name = "[Release Build]"

    buildType(ApacheIgniteReleaseJava8_PrepareVote1NetCpp)

})