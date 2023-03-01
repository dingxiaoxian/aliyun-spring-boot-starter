rootProject.name = extra["rootProjectName"] as String
val buildFiles = fileTree(rootDir) {
    include("**/*.gradle.kts")
    exclude("build", "**/gradle", "settings.gradle.kts", "buildSrc", "/build.gradle.kts", "out")
}
//该配置未检验过多层文件夹项目
buildFiles.forEach { buildFile ->
    run {
        val isDefaultName = buildFile.name.equals("build.gradle.kts")
        val projectName: String = if (isDefaultName) {
            val projectFile = buildFile.parentFile.relativeTo(rootDir)
            projectFile.path.replace(File.separator, ":")
        } else {
            buildFile.name.removeSuffix(".gradle.kts")
        }
        val projectPath = ":$projectName"
        include(projectPath)
        if (!isDefaultName) {
            val project = findProject(projectPath)!!
            project.name = projectName
            project.projectDir = buildFile.parentFile
            project.buildFileName = buildFile.name
        }
    }
}