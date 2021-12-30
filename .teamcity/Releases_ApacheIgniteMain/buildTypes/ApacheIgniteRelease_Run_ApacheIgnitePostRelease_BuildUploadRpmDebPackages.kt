package Releases_ApacheIgniteMain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*


object ApacheIgniteRelease_Run_ApacheIgnitePostRelease_BuildUploadRpmDebPackages :BuildType({
    name = "-> Run [#2.2] :: Apache Ignite Post Release | Build & Upload RPM / DEB Packages"
})

