package ignite2_Release_ApacheIgniteNightly

import ignite2_Release_ApacheIgniteNightly.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ignite2_Release_ApacheIgniteNightly")
    name = "[Nightly]"

    buildType(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleasePrepare)
    buildType(ignite2_Release_NightlyRelease_ApacheIgniteNightlyRelease3AssemblePackages)
    buildType(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleaseAssembleDockerImage)
    buildType(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries)
    buildType(ignite2_Release_NightlyRelease_RunApacheIgniteNightlyRelease)
    buildType(ignite2_Release_NightlyRelease_ApacheIgniteNightlyReleaseBuildNetCpp)
})
