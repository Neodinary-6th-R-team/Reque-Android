plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.pluginGradle)
    compileOnly(libs.kotlin.pluginGradle)
}

gradlePlugin {
    plugins {
        register("AndroidHilt") {
            id = "reque.hilt"
            implementationClass = "com.neodinary.reque.convention.AndroidHiltConventionPlugin"
        }
        register("AndroidApplication") {
            id = "reque.application"
            implementationClass = "com.neodinary.reque.convention.AndroidApplicationConventionPlugin"
        }
        register("AndroidData") {
            id = "reque.data"
            implementationClass = "com.neodinary.reque.convention.AndroidDataConventionPlugin"
        }
        register("AndroidFeature") {
            id = "reque.feature"
            implementationClass = "com.neodinary.reque.convention.AndroidFeatureConventionPlugin"
        }
        register("AndroidLibrary") {
            id = "reque.android.library"
            implementationClass = "com.neodinary.reque.convention.AndroidLibraryConventionPlugin"
        }
        register("AndroidCommon") {
            id = "reque.common"
            implementationClass = "com.neodinary.reque.convention.AndroidCommonConventionPlugin"
        }
        register("JavaLibrary") {
            id = "reque.java.library"
            implementationClass = "com.neodinary.reque.convention.JavaLibraryConventionPlugin"
        }
    }
}