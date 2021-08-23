package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunMl : BuildType({
    name = "-> Run :: ML"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Examples) {
        }
        snapshot(Javadoc) {
        }
        snapshot(LicensesHeaders) {
        }
        snapshot(Ml) {
        }
    }
})
