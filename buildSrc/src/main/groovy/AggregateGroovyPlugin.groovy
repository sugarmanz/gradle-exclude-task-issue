import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.GradleBuild

class AggregateGroovyPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.task('all', type: GradleBuild) {
            startParameter = project.getGradle().startParameter.newInstance()

            tasks = [
                    "first" as String,
                    "second" as String,
            ]
        }

        project.task('first') doLast this.&first
        project.task('second') doLast this.&second
    }

    void first() {
        println("first")
    }

    void second() {
        println("second")
    }

}
