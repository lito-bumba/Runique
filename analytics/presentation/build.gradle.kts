plugins {
    alias(libs.plugins.runique.android.feature.ui)
}

android {
    namespace = "com.bumba.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
}