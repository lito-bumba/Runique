package com.bumba.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick : AnalyticsAction
}