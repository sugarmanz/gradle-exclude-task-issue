import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.GradleBuild
import org.gradle.kotlin.dsl.task

class AggregatePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.task<GradleBuild>("container") {
            tasks = listOf(
                "first",
                "second"
            )
        }

        project.task("first") {
            doLast {
                first()
            }
        }

        project.task("second") {
            doLast {
                second()
            }
        }

    }

    fun first() {
        println("first")
    }

    fun second() {
        println("second")
    }

}
