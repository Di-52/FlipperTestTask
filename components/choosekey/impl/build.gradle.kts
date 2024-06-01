plugins {
    id("flipper.android-compose")
    id("flipper.anvil")
}

android.namespace = "com.lionzxy.flippertesttask.keychoose.impl"

dependencies {
    implementation(projects.components.choosekey.api)

    implementation(projects.components.core.di)
    implementation(projects.components.core.decompose)

    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.decompose)
    implementation(libs.bundles.decompose)
    implementation (libs.system.ui.controller)

    implementation(libs.kotlin.immutable.collections)
}