package IgniteTests24Java8_Dev.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object IgniteTests24Java8_Dev_Test1 : BuildType({
    name = "test1"
    description = "test1"

    steps {
        script {
            name = "show cpu"
            scriptContent = "cat /proc/cpuinfo"
        }
    }
})
