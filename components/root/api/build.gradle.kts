plugins {
    id("flipper.android-compose")
    id("flipper.anvil")
}

android.namespace = "com.lionzxy.flippertesttask.root.api"

dependencies {
    implementation(projects.components.core.decompose)
    implementation(libs.decompose)
    implementation(libs.compose.ui)
}