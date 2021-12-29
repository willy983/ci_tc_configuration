package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

object IgniteTests24Java8_ServiceGridOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Service Grid"

    params {
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("MAVEN_MODULES", ":ignite-schedule,:ignite-jcl,:ignite-log4j,:ignite-log4j2,:ignite-slf4j,:ignite-core")
        param("TEST_SUITE", "IgniteServiceGridTestSuite,IgniteServiceConfigVariationsFullApiTestSuite")
    }

    steps {
        maven {
            name = "Run test suite"
            id = "RUNNER_265"
            goals = "%MAVEN_GOALS%"
            runnerArgs = """
                -P all-java,scala-2.10,tensorflow,scala,scala-test,surefire-fork-count-1
                -pl %MAVEN_MODULES% -am
                -Dmaven.test.failure.ignore=true
                -DfailIfNoTests=false
                -Dtest=%TEST_SUITE%
                -Dmaven.javadoc.skip=true
                %MAVEN_OPTS%
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            userSettingsPath = "settings.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = """
                -ea
                -server
                -Xms%XMS%
                -Xmx%XMX%
                -XX:+HeapDumpOnOutOfMemoryError
                -XX:+AggressiveOpts
                -DIGNITE_TEST_HOME="%teamcity.build.workingDir%"
                -DIGNITE_UPDATE_NOTIFIER=false
                -DIGNITE_NO_DISCO_ORDER=true
                -DIGNITE_PERFORMANCE_SUGGESTIONS_DISABLED=true
                -DIGNITE_QUIET=false
                -Djava.net.preferIPv4Stack=true
                -DTEST_SCALE_FACTOR=%TEST_SCALE_FACTOR%
                %JVM_ARGS% %JVM_EXTRA_ARGS%
            """.trimIndent()
        }
        stepsOrder = arrayListOf("RUNNER_264", "RUNNER_287", "RUNNER_225", "RUNNER_265", "RUNNER_266")
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})