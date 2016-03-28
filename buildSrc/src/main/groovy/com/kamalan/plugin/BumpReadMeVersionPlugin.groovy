package com.kamalan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Updates ReadMe file with the latest version
 * Created by Hesam on 23 March 2016
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class BumpReadMeVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create('bumpReadMeVersion', VersionPluginExtension)

        project.afterEvaluate {
            project.task('bumpVersion', type: BumpVersion) {
                readMe = new File(project.rootDir.absolutePath + "/README.md")
                versionCode = project.android.defaultConfig.versionCode
                versionName = project.android.defaultConfig.versionName
            }
        }
    }
}