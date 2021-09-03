plugins {
    id("net.researchgate.release") version "2.6.0"
}

release {
    failOnUpdateNeeded = false
    failOnCommitNeeded = false
    failOnPublishNeeded = false
    failOnSnapshotDependencies = false
}

tasks {
    val build by creating {
        doLast {
            println("build")
        }
    }

    val afterBuild by creating {
        doLast {
            println("afterBuild")
        }

        mustRunAfter(build)
    }

//    build.finalizedBy(afterBuild)


    task<GradleBuild>("all") {
        tasks = listOf(
            build.name,
            afterBuild.name
        )
    }
}