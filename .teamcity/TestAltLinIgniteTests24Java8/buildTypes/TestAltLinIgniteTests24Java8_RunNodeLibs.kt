package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_RunNodeLibs : Template({
    name = "Run node libs"

    dependencies {
        dependency(TestAltLinIgniteTests24Java8_Build) {
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
