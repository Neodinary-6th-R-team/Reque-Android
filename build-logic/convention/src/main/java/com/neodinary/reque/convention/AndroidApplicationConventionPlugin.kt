package com.neodinary.reque.convention

import com.android.build.api.dsl.ApplicationExtension
import com.neodinary.reque.convention.base.configureAndroidCompose
import com.neodinary.reque.convention.base.configureCommonAndroid
import com.neodinary.reque.convention.base.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

/**
 * app 모듈에 사용되는 plugin에 대한 정보
 * */
class AndroidApplicationConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
                apply("androidx.navigation.safeargs.kotlin")
                // 여기서 사용되는 플러그인은 bulid-logic에 있는 AndroidHiltConventionPlugin
                apply("reque.hilt")
            }

            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())

            // Application 정보를 추가하기위해 'ApplicatoinExtension'을 가져옴
            extensions.configure<ApplicationExtension> {
                // app 모듈은 모든 정보가 필요함
                configureKotlinAndroid(this)
                configureCommonAndroid(this)
                configureAndroidCompose(this)

                defaultConfig {
                    applicationId = "com.neodinary.reque"
                    versionCode = 20240601
                    versionName = "1.0.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    targetSdk = 34
                    minSdk = 24

                    buildFeatures {
                        // gradle 8.0부터 buildConfig를 사용하기 위함
                        buildConfig = true
                    }

                    dataBinding {
                        enable = true
                    }

                    buildConfigField("String", "KAKAO_NATIVE_KEY", properties.getProperty("KAKAO_NATIVE_KEY"))
                    manifestPlaceholders["KAKAO_NATIVE_KEY"] = properties.getProperty("KAKAO_NATIVE_KEY")
                }

                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
                dependencies {
                    add("implementation", project(":feature:splash"))
                    add("implementation", project(":common-ui"))
                    add("implementation", project(":domain"))
                    add("implementation", project(":feature:dopamine"))

                    // Android Common
                    add("implementation", libs.findLibrary("androidx-core").get())
                    add("implementation", libs.findLibrary("androidx-appcompat").get())
                    add("implementation", libs.findLibrary("android-material").get())

                    // kakao
                    add("implementation", libs.findLibrary("kakao-sdk").get())

                    // Android Ui 관련
                    add("implementation", libs.findLibrary("androidx-constraintlayout").get())
                    add("implementation", libs.findLibrary("facebook-shimmer").get())

                    // Network
                    add("implementation", libs.findLibrary("squareup-retrofit2").get())
                    add("implementation", libs.findLibrary("squareup-okhttp").get())
                    add("implementation", libs.findLibrary("squareup-okhttp-interceptor").get())

                    // Glide
                    add("implementation", libs.findLibrary("glide-glide").get())
                    add("implementation", libs.findLibrary("glide-compiler").get())

                    // Navigation
                    add("implementation", libs.findBundle("androidx-navigation").get())
                    add("implementation", libs.findLibrary("androidx-navigation-test").get())

                    // Coroutine Scope
                    add("implementation", libs.findBundle("lifecycle").get())
                    add("implementation", libs.findBundle("kotlinx-coroutine").get())


                    // Data Store
                    add("implementation", libs.findBundle("datastore").get())
                }
            }
        }
    }
}