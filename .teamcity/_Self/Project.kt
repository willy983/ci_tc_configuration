package _Self

import _Self.buildTypes.*
import _Self.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.jira

object Project : Project({
    description = "Contains all other projects"

    vcsRoot(GitHubApacheIgnite)
    vcsRoot(GitHubApacheIgnite3)
    vcsRoot(GitHubApacheIgnitePhpThinClient)
    vcsRoot(GitHubApacheIgnitePythonThinClient)
    vcsRoot(GitHubApacheIgniteExtensions)
    vcsRoot(GitHubApacheIgniteRelease)
    vcsRoot(GitHubApacheIgniteNodejsThinClient)
    vcsRoot(GitHubH2fork4igniteSqlEngine)
    vcsRoot(CiTcConfiguration)

    template(ThinClientStopIgnite)
    template(Tests_IgniteThinClients_PreBuild)
    template(ThinClientStartIgnite)
    template(RunNodeJsTests)
    template(RunPhpTests)
    template(RunPythonTestsBasic)
    template(Tests_IgniteThinClients_PostBuild)
    template(RunPythonTestsSsl)

    features {
        buildReportTab {
            id = "PROJECT_EXT_1"
            title = "Code Coverage"
            startPage = "coverage.zip!index.html"
        }
        buildReportTab {
            id = "PROJECT_EXT_2"
            title = "JavaDoc"
            startPage = "javadoc.zip!index.html"
        }
        jira {
            id = "PROJECT_EXT_3"
            displayName = "Apache JIRA"
            host = "http://issues.apache.org/jira"
            userName = ""
            password = ""
            projectKeys = "IGNITE"
        }
        buildReportTab {
            id = "PROJECT_EXT_4"
            title = "JaCoCo Code Coverage"
            startPage = "site-aggregate/index.html"
        }
        feature {
            id = "PROJECT_EXT_5"
            type = "CloudIntegration"
            param("enabled", "false")
        }
        feature {
            id = "PROJECT_EXT_7"
            type = "CloudImage"
            param("use-spot-instances", "false")
            param("user-tags", "dep=dev,username=lexa,target=teamcity")
            param("agent_pool_id", "0")
            param("image-instances-limit", "1")
            param("subnet-id", "subnet-6316d63a")
            param("ebs-optimized", "false")
            param("instance-type", "t2.large")
            param("source-id", "ami-56d4ad31")
            param("image-name-prefix", "EC2-AGENT")
            param("profileFeatureId", "PROJECT_EXT_6")
            param("key-pair-name", "ec2_agent_keys")
            param("security-group-ids", "sg-e1620086,")
            param("profileId", "amazon-1")
        }
        feature {
            id = "amazon-1"
            type = "CloudProfile"
            param("source_images_json", """[{"image-name-prefix":"EC2-AGENT","key-pair-name":"ec2_agent_keys","use-spot-instances":"false","security-group-ids":"sg-e1620086,","user-tags":"dep=dev,username=lexa,target=teamcity","subnet-id":"subnet-6316d63a","image-instances-limit":"1","agent_pool_id":"0","ebs-optimized":"false","instance-type":"t2.large","source-id":"ami-56d4ad31"}]""")
            param("secure:access-id", "credentialsJSON:bbc02895-c5c3-44c7-b20b-a6bbdb1644c9")
            param("system.cloud.profile_id", "amazon-1")
            param("description", "")
            param("user-script", "")
            param("cloud-code", "amazon")
            param("terminate-after-build", "false")
            param("endpoint-url", "ec2.ap-northeast-1.amazonaws.com")
            param("enabled", "true")
            param("max-running-instances", "1")
            param("agentPushPreset", "1486034839928")
            param("profileId", "amazon-1")
            param("name", "ec2_agents")
            param("secure:secret-key", "credentialsJSON:0cceecb8-3b62-4a5d-a8b3-c052f205e917")
            param("terminate-idle-time", "30")
            param("not-checked", "")
        }
    }

    cleanup {
        baseRule {
            all(days = 16)
            history(days = 16)
            artifacts(days = 16, artifactPatterns = """
                +:**/*
                +:.teamcity/**
            """.trimIndent())
            preventDependencyCleanup = false
        }
    }

    subProject(IgniteExtensions.Project)
    subProject(Releases.Project)
    subProject(IgniteTests24Java8.Project)
    subProject(TestAltLinIgniteTests2xJdk811.Project)
    subProject(ignite3.Project)
    subProject(ignite2.Project)
    subProject(IgniteThinClients.Project)
    subProject(DevAi2x.Project)
})
