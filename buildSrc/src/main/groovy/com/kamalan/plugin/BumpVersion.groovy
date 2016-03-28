package com.kamalan.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

@SuppressWarnings("GroovyUnusedDeclaration")
public class BumpVersion extends DefaultTask {

    def readMe
    def versionCode
    def versionName

    File getReadMe() {
        project.file(readMe)
    }

    BumpVersion() {
        group = 'my plugin'
    }

    @TaskAction
    def bump() {
        description = 'Updates ReadMe file with the latest version'

        File readMeFile = getReadMe()
        String contents = readMeFile.getText('UTF-8')
        contents = contents.replaceAll("Version_Code:.*", "Version_Code:${versionCode}")
        contents = contents.replaceAll("Version_Name:.*", "Version_Name:${versionName}")
        readMeFile.write(contents, 'UTF-8')
    }

}