package patches.projects

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    cleanup {
        expect {
            baseRule {
                all(days = 28)
            }
        }
        update {
            baseRule {
                all(days = 22)
            }
        }
        expect {
            baseRule {
                history(days = 28)
            }
        }
        update {
            baseRule {
                history(days = 26)
            }
        }
        expect {
            baseRule {
                artifacts(days = 28, artifactPatterns = """
                    +:**/*
                    +:.teamcity/**
                """.trimIndent())
            }
        }
        update {
            baseRule {
                artifacts(days = 22, artifactPatterns = """
                    +:**/*
                    +:.teamcity/**
                """.trimIndent())
            }
        }
    }
}
