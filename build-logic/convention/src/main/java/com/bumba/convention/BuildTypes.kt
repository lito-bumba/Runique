package com.bumba.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    commonExtension.run {

        buildFeatures {
            buildConfig = true
        }

        val baseUrl = gradleLocalProperties(rootDir, project.providers).getProperty("BASE_URL")
        val apiKey = gradleLocalProperties(rootDir, project.providers).getProperty("API_KEY")

        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(
                                baseUrl = baseUrl,
                                apiKey = apiKey
                            )
                        }
                        release {
                            configureReleaseBuildType(
                                commonExtension = commonExtension,
                                baseUrl = baseUrl,
                                apiKey = apiKey
                            )
                        }
                    }
                }
            }

            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(
                                baseUrl = baseUrl,
                                apiKey = apiKey
                            )
                        }
                        release {
                            configureReleaseBuildType(
                                commonExtension = commonExtension,
                                baseUrl = baseUrl,
                                apiKey = apiKey
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(baseUrl: String, apiKey: String) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    baseUrl: String,
    apiKey: String
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"$baseUrl\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}