package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_JCacheTck11 : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "JCache TCK 1.1"

    params {
        text("MAVEN_OPTS", "-Djavax.cache.tck.version=1.1.0", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_GOALS", "test", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-core -am", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_PROFILES", "!release,jcache-tck", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
        nonZeroExitCode = false
    }
    
    disableSettings("ARTIFACT_DEPENDENCY_103", "RUNNER_287")
})
