package ignite2_Release_ApacheIgniteMain_ReleaseBuild

import ignite2_Release_ApacheIgniteMain_ReleaseBuild.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ignite2_Release_ApacheIgniteMain_ReleaseBuild")
    name = "[Release Build]"

    buildType(ignite2_Release_ApacheIgniteMain_ReleaseBuild_PrepareBuildOdbc)
})
