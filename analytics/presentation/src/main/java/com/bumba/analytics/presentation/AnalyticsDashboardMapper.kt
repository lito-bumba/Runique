package com.bumba.analytics.presentation

import com.bumba.analytics.domain.AnalyticsValue
import com.bumba.core.presentation.ui.formatted
import com.bumba.core.presentation.ui.toFormattedKm
import com.bumba.core.presentation.ui.toFormattedKmh
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit


fun Duration.toFormattedTotalTime(): String {
    val days = toLong(DurationUnit.DAYS)
    val hours = toLong(DurationUnit.HOURS) % 24
    val minutes = toLong(DurationUnit.MINUTES) % 60

    return "${days}d ${hours}h ${minutes}m"
}

fun AnalyticsValue.toAnalyticsDashboardState(): AnalyticsDashboardState {
    return AnalyticsDashboardState(
        totalDistanceRun = (totalDistanceRun / 1000.0).toFormattedKm(),
        totalTimeRun = totalTimeRun.toFormattedTotalTime(),
        fastestEverRun = fastestEverRun.toFormattedKmh(),
        avgDistance = (avgDistancePerRun / 1000.0).toFormattedKm(),
        avgPace = avgPacePerRun.seconds.formatted()
    )
}