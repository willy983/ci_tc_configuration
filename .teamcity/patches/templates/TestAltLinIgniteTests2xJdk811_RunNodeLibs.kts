package patches.templates

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Template
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a template with id = 'TestAltLinIgniteTests2xJdk811_RunNodeLibs'
in the project with id = 'TestAltLinIgniteTests2xJdk811', and delete the patch script.
*/
create(RelativeId("TestAltLinIgniteTests2xJdk811"), Template({
    id("TestAltLinIgniteTests2xJdk811_RunNodeLibs")
    name = "Run node libs"

    dependencies {
        dependency(RelativeId("TestAltLinIgniteTests2xJdk811_Build")) {
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
}))
