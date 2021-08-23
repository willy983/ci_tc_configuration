package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunMlTensorFlow : BuildType({
    name = "-> Run :: ML (TensorFlow)"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Javadoc) {
        }
        snapshot(LicensesHeaders) {
        }
        snapshot(TensorFlow) {
        }
    }
})
