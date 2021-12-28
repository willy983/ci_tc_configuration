package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_ZookeeperIpFinder : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "Zookeeper Ip Finder"

    params {
        text("DIR_EXTENSION", "zookeeper-ip-finder-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
