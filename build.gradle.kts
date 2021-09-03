tasks {
    val first by creating {
        doLast {
            println("first")
        }
    }

    val second by creating {
        doLast {
            println("second")
        }

        dependsOn(first)
    }

    task<GradleBuild>("both") {
        startParameter = gradle.startParameter.newInstance().apply {
            println("excluded tasks: $excludedTaskNames")
        }

        tasks = listOf(
            first.name,
            second.name
        )

        doLast {
            println("both")
        }
    }
}
