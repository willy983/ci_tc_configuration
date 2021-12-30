package Releases_ApacheIgniteMain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

object ApacheIgniteReleaseJava8_PrepareVote : BuildType({
    name = "-> Run [#1.1] :: Apache Ignite Release Vote | Full Assembly"
    description = "Assemble Apache Ignite release artifacts"

    artifactRules = "release => release-%reverse.dep.*.IGNITE_VERSION%%reverse.dep.*.RC_NAME%.zip"

    params {
        text("ASSEMBLY_HADOOP", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("reverse.dep.*.IGNITE_VERSION", "", label = "Ignite version", description = "Ex.: 2.4.0", display = ParameterDisplay.PROMPT,
              regex = "[0-9]+.[0-9]+.0", validationMessage = "Version should be '<major>.<minor>.0'")
        text("ASSEMBLY_AI", "", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.JAVA_HOME", "%env.JDK_ORA_8%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("system.JAVA_HOME", "%env.JAVA_HOME%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("reverse.dep.*.RC_NAME", "", label = "Release Candidate index", description = "Ex.: -rc1", display = ParameterDisplay.PROMPT,
              regex = "-rc[0-9]+", validationMessage = "Release Candidate index should be '-rc<index>'")
    }

    vcs {
        root(RelativeId("GitHubApacheIgniteRelease"), "+:. => ./ignite-release")
        root(RelativeId("GitHubApacheIgnite"), "+:. => ./ignite")

        cleanCheckout = true
    }

    steps {
        script {
            name = "Copy release scripts"
            scriptContent = """
                mkdir release
                cp -r ignite-release/scripts/* release/
            """.trimIndent()
        }
        script {
            name = "Git setup (Ignore chmod settings and user/email)"
            workingDir = "ignite"
            scriptContent = """
                git config core.filemode false
                git config --global user.name "Ignite Teamcity"
                git config --global user.email ignite@apache.org
            """.trimIndent()
        }
        script {
            name = "Git patch (with changed versions) applying"
            workingDir = "ignite"
            scriptContent = """
                git apply ../patch/vote.patch
                git commit -a -m "version changed to: %reverse.dep.*.IGNITE_VERSION%"
            """.trimIndent()
        }
        script {
            name = "Fix .git/config"
            scriptContent = """
                #!/usr/bin/env bash
                set -x
                
                sed -i -r -e 's|^\[url.*||' -e 's|.*nsteadOf.*||' -e '/^${'$'}/d' ignite/.git/config
            """.trimIndent()
        }
        script {
            name = "Zip sources"
            scriptContent = """
                mkdir release/svn
                mkdir release/svn/vote
                
                cd ignite
                git archive HEAD --prefix=apache-ignite-%reverse.dep.*.IGNITE_VERSION%-src/ --format=zip -o ../release/svn/vote/apache-ignite-%reverse.dep.*.IGNITE_VERSION%-src.zip
            """.trimIndent()
        }
        script {
            name = "Save Git repo (Release tag preparation)"
            scriptContent = "cp -r ignite release/git/"
        }
        script {
            name = "Create properties file"
            scriptContent = """
                echo "rc_name=\"%reverse.dep.*.RC_NAME%\"" > release/release.properties
                echo "ignite_version=\"%reverse.dep.*.IGNITE_VERSION%\"" >> release/release.properties
            """.trimIndent()
        }
        maven {
            name = "[TO BE REMOVED, IGNITE-6727] Install tools"
            goals = "install"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-pl modules/tools -am"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
        }
        maven {
            name = "Deploy Java8 (ML)"
            enabled = false
            goals = "clean deploy"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-Pml -DskipTests -pl modules/ml -am -DaltDeploymentRepository=local::default::file:release/maven"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        maven {
            name = "Deploy All"
            goals = "clean deploy"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-Pall-java,all-scala,licenses -DclientDocs -DskipTests -DaltDeploymentRepository=local::default::file:release/maven"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        maven {
            name = "Generate javadoc"
            goals = "initialize"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-Pjavadoc"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        script {
            name = "Copy dotnetdocs"
            scriptContent = """
                cp -r dotnetdoc ignite/modules/clients/target
                
                rm -rf dotnetdoc
            """.trimIndent()
        }
        maven {
            name = "Assembly hadoop"
            enabled = false
            goals = "initialize"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-Prelease -Dignite.edition=apache-ignite-hadoop"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        maven {
            name = "Assembly fabric"
            goals = "initialize"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-Prelease,all-scala -Dignite.edition=apache-ignite"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jvmArgs = "-Xmx1g -XX:MaxPermSize=512m"
        }
        script {
            name = "Copy binaries and make checkums"
            scriptContent = """
                cp ignite/target/bin/*.* release/svn/vote
                
                cd release/svn/vote
                
                list=${'$'}(find . -type f -name "*.zip")
                
                for file in ${'$'}list
                do
                    base_name=${'$'}(basename ${'$'}file)
                    sha512sum ${'$'}base_name > ${'$'}base_name.sha512
                done
            """.trimIndent()
        }
    }

    dependencies {
        dependency(RelativeId("ApacheIgniteReleaseJava8_PrepareVote1NetCpp")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = """
                    ignite.dotnet.bin.zip!** => ignite/modules/platforms/dotnet/bin
                    ignite.odbc.installers.zip!** => ignite/modules/platforms/cpp/bin/odbc
                    dotnetdoc.zip!** => dotnetdoc
                    vote.patch => patch
                """.trimIndent()
            }
        }
    }

    requirements {
        contains("teamcity.agent.jvm.os.name", "Linux")
    }
})

