package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'ignite2_Release_ApacheIgniteReleaseJava8_IgniteRelease72CheckFileConsistency'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("ignite2_Release_ApacheIgniteReleaseJava8_IgniteRelease72CheckFileConsistency")) {
    dependencies {
        remove(RelativeId("ignite2_Release_ApacheIgniteMain_ReleaseBuild_1")) {
            artifacts {
                buildRule = lastSuccessful("ignite-%IGNITE_VERSION%")
                artifactRules = "release*.zip!svn/vote** => raw/cur"
            }
        }

        add(RelativeId("ignite2_Release_ApacheIgniteMain_ReleaseBuild")) {
            artifacts {
                buildRule = lastSuccessful()
                artifactRules = "release*.zip!svn/vote** => raw/cur"
            }
        }

    }
}
