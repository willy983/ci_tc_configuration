package IgniteExtensions_Tests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteExtensions_Tests_Gce : BuildType({
    templates(IgniteExtensions_Tests_RunExtensionTests)
    name = "GCE"

    params {
        text("DIR_EXTENSION", "gce-ext", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("env.test.gce.project.name", "449058130467")
        password("env.test.gce.account.id", "credentialsJSON:7ec0ca3e-dcf3-43f2-9547-842660b8b637")
        param("env.test.gce.p12.path", "/root/.gce/gridgain-gce-key.p12")
    }
})
