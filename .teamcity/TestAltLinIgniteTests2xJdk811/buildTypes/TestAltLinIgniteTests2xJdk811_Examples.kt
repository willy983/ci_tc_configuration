package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Examples : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Examples"

    params {
        text("EXTRA_MAVEN_PROFILES", "-P scala-test", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("JVM_ARGS", """
            -Djava.awt.headless=true
            -Dawt.toolkit=sun.awt.HToolkit
        """.trimIndent(), display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteExamplesSelfTestSuite,IgniteExamplesSparkSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }

    dependencies {
        artifacts(TestAltLinIgniteTests2xJdk811_Build) {
            id = "ARTIFACT_DEPENDENCY_15"
            artifactRules = "examples.zip!** => ."
            enabled = false
        }
    }
})
