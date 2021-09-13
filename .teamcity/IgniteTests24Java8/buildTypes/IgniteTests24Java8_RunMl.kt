package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_RunMl : BuildType({
    name = "-> Run :: ML"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(IgniteTests24Java8_Examples) {
        }
        snapshot(IgniteTests24Java8_Javadoc) {
        }
        snapshot(IgniteTests24Java8_LicensesHeaders) {
        }
        snapshot(IgniteTests24Java8_Ml) {
        }
    }
})
