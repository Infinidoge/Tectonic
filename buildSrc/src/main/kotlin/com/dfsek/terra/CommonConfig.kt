import org.gradle.api.Project
import java.io.ByteArrayOutputStream

fun Project.configureCommon() {
    //configureDependencies()
    //configureCompilation()
    //configureDistribution()

    version = rootProject.version
}

fun Project.getGitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = mutableListOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

fun Project.gitClone(name: String) {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = mutableListOf("git", "clone", name)
        standardOutput = stdout
    }
}