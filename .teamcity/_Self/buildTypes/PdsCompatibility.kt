package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

object PdsCompatibility : BuildType({
    templates(RunTestSuitesJava)
    name = "PDS (Compatibility)*"
    description = "* (forced JDK8)"

    params {
        param("MAVEN_GOALS", "surefire:test")
        param("MAVEN_MODULES", ":ignite-compatibility")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "IgniteCompatibilityBasicTestSuite")
    }

    steps {
        maven {
            name = "Run test suite* (forced JDK8, removed %JVM_EXTRA_ARGS%)"
            id = "RUNNER_265"
            goals = "%MAVEN_GOALS%"
            runnerArgs = """
                -P all-java,all-other,scala-2.10,tensorflow,scala,scala-test
                %EXTRA_MAVEN_PROFILES%
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
            jdkHome = "%env.JDK_ORA_8%"
            jvmArgs = """
                -ea
                -server
                -Xms%XMS%
                -Xmx%XMX%
                -XX:+HeapDumpOnOutOfMemoryError
                -XX:+AggressiveOpts
                -DIGNITE_HOME=%teamcity.build.workingDir%
                -DIGNITE_TEST_HOME=%teamcity.build.workingDir%
                -DIGNITE_UPDATE_NOTIFIER=false
                -DIGNITE_NO_DISCO_ORDER=true
                -DIGNITE_PERFORMANCE_SUGGESTIONS_DISABLED=true
                -Djava.net.preferIPv4Stack=true
                -DTEST_SCALE_FACTOR=%TEST_SCALE_FACTOR%
                %IGNITE_LOGGING_OPTS%
                %JVM_ARGS%
            """.trimIndent()
        }
        stepsOrder = arrayListOf("RUNNER_264", "RUNNER_287", "RUNNER_225", "RUNNER_265", "RUNNER_266")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})
