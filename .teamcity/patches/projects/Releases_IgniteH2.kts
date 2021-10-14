package patches.projects

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a project with id = 'Releases_IgniteH2'
in the project with id = 'Releases', and delete the patch script.
*/
create(RelativeId("Releases"), Project({
    id("Releases_IgniteH2")
    name = "Ignite H2"
}))
