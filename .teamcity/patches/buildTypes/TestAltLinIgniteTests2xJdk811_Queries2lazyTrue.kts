package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'TestAltLinIgniteTests2xJdk811_Queries2lazyTrue'
in the project with id = 'TestAltLinIgniteTests2xJdk811', and delete the patch script.
*/
create(RelativeId("TestAltLinIgniteTests2xJdk811"), BuildType({
    templates(RelativeId("TestAltLinIgniteTests2xJdk811_RunTestsJava"))
    id("TestAltLinIgniteTests2xJdk811_Queries2lazyTrue")
    name = "Queries 2 (lazy=true)"

    params {
        text("system.IGNITE_QUERY_LAZY_DEFAULT", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinaryCacheQueryLazyTestSuite2", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 240
    }
}))

