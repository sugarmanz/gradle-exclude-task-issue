# Gradle Exclude Task From `GradleBuild`

This repo shows breaking behavior for Gradle +6.8 in regard to task exclusion. When running a Gradle task, one can specify tasks to exclude from execution per the [docs](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:excluding_tasks_from_the_command_line).

```
./gradlew someTask -x someTaskThatWouldNormallyBeExecuted
```

This Gradle project has three main tasks:
1. `first`: Some task that prints `first`
2. `second`: Some task that prints `second` and `dependsOn` `first`
3. `both`: A `GradleBuild` task that prints `both`, includes both the `first` and `second` tasks, and ensures to propagate the start parameters with `startParameter = gradle.startParameter.newInstance()`

### Expected Behavior

This is the behavior I get on Gradle v6.7.1:
```
./gradlew first
// first

./gradlew second
// first
// second

./gradlew second -x first
// second

./gradlew both
// first
// second
// both

./gradlew both -x first
// second
// both

./gradlew both -x second
// first
// both
```

This is the behavior I get on Gradle v6.8:
```
./gradlew first
// first

./gradlew second
// first
// second

./gradlew second -x first
// second

./gradlew both
// first
// second
// both

./gradlew both -x first
// first (This shouldn't have executed)
// second
// both

./gradlew both -x second
// first
// second (This shouldn't have executed)
// both
```

The problematic behavior is when trying to exclude tasks when executing the `GradleBuild` task. I checked the `startParameter` to ensure the `excludedTaskNames` is still set, and indeed it is.
