plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serializatoin)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)

            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.exoplayer.dash)
            implementation(libs.androidx.media3.ui)
            implementation(libs.koin.android)
            implementation(libs.koin.android.compose)
            implementation(libs.ktor.client.android)

        }

        iosMain.dependencies {
            //dependsOn(commonMain)
            implementation(libs.ktor.client.darwin)
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)

            implementation(libs.compose.foundation)
            implementation(libs.compose.material)
            implementation(libs.compose.material3)
            //implementation(libs.compose.preview)
            implementation(libs.compose.components.resources)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation (libs.ktor.client.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.compose)
            implementation(libs.kamel.image)

            implementation(libs.moko.mvvm)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)
            implementation("co.touchlab:kermit:2.0.2")

            implementation("io.coil-kt.coil3:coil:3.0.0-alpha01")
            implementation("io.coil-kt.coil3:coil-network:3.0.0-alpha01")
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha01")

        }
    }
}

android {
    namespace = "com.jiostb.myapplication"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
dependencies {
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.compose)
}
