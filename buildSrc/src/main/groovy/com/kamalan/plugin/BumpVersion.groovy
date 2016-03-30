package com.kamalan.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

@SuppressWarnings("GroovyUnusedDeclaration")
public class BumpVersion extends DefaultTask {

    def readMe
    def buildGradle
    def versionCode
    def versionName

    File getReadMe() {
        project.file(readMe)
    }

    File getBuildGradle() {
        project.file(buildGradle)
    }

    BumpVersion() {
        group = 'my plugin'
    }

    @TaskAction
    def bump() {
        description = 'Updates ReadMe file with the latest version as well as default values of Android version code and version name in build.gradle file'

        // Update readme file
        File readMeFile = getReadMe()
        String contents = readMeFile.getText('UTF-8')
        contents = contents.replaceAll("Version_Code:.*", "Version_Code:${versionCode}")
        contents = contents.replaceAll("Version_Name:.*", "Version_Name:${versionName}")
        readMeFile.write(contents, 'UTF-8')

        // Update build.gradle file
        File buildGradleFile = getBuildGradle()
        contents = buildGradleFile.getText('UTF-8')
        contents = contents.replaceAll("android_version_code=.*", "android_version_code=${versionCode}")
        contents = contents.replaceAll("android_version_name=.*", "android_version_name=\"${versionName}\"")
        buildGradleFile.write(contents, 'UTF-8')
    }

}