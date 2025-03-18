package com.bumba.analytics.domain

interface AnalyticsRepository {

    suspend fun getAnalyticsValues(): AnalyticsValue
}