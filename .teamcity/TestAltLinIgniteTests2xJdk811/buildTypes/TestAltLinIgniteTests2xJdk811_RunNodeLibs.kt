package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_RunNodeLibs : Template({
    name = "Run node libs"

    dependencies {
        dependency(TestAltLinIgniteTests2xJdk811_Build) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_18"
                artifactRules = "run.zip!** => ."
            }
        }
    }
})
