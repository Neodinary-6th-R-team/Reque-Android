pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "Reque"
include(":app")
include(":feature")
include(":feature:splash")
include(":common-ui")
include(":domain")
include(":data")
include(":feature:home")
include(":feature:login")
include(":feature:mission-record")
include(":feature:dopamine")
include(":feature:mypage")
