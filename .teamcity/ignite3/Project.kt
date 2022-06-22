package ignite3

import ignite3.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ignite3")
    name = "[Apache Ignite 3.x]"

    buildType(ignite3_BuildApacheIgnite)

    template(ignite3_ApacheIgniteBuildDependency)

    params {
        text("env.JAVA_HOME", "%env.JDK_ORA_11%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    subProject(ignite3_Test.Project)
    subProject(ignite3_Release.Project)
})
