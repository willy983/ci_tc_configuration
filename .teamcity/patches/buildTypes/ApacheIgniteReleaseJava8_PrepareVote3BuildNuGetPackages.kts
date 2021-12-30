package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'ApacheIgniteReleaseJava8_PrepareVote3BuildNuGetPackages'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("ApacheIgniteReleaseJava8_PrepareVote3BuildNuGetPackages")) {
    dependencies {
        remove(RelativeId("Releases_ApacheIgniteMain_ReleaseBuild")) {
            artifacts {
                buildRule = build("%VOTE_BUILD_NUM%")
                cleanDestination = true
                artifactRules = """
                    release*.zip!svn/vote/apache-ignite-*-src.zip
                    release*.zip!svn/vote/apache-ignite-*-bin.zip
                """.trimIndent()
            }
        }

        add(RelativeId("Releases_ApacheIgniteMain_ReleaseBuild_1")) {
            artifacts {
                buildRule = build("%VOTE_BUILD_NUM%")
                cleanDestination = true
                artifactRules = """
                    release*.zip!svn/vote/apache-ignite-*-src.zip
                    release*.zip!svn/vote/apache-ignite-*-bin.zip
                """.trimIndent()
            }
        }

    }
}
