package ignite3_Test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ignite3_Test_RunIntegrationTests : BuildType({
    name = "-> Run :: Integration Tests"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite3)

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(ignite3_Test_IntegrationTests.buildTypes.ignite3_Test_IntegrationTests_IntegrationTests) {
        }
    }
})
