plugins{
    id("reque.java.library")
}

dependencies {
    implementation(libs.kotlin.coroutine.core)
    implementation(libs.kotlin.stdlib)
    implementation(libs.javax.inject)
}