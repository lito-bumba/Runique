package com.bumba.run.presentation.run_overview.mapper

import com.bumba.core.domain.run.Run
import com.bumba.core.presentation.ui.formatted
import com.bumba.core.presentation.ui.toFormattedKm
import com.bumba.core.presentation.ui.toFormattedKmh
import com.bumba.core.presentation.ui.toFormattedMeters
import com.bumba.core.presentation.ui.toFormattedPace
import com.bumba.run.presentation.run_overview.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())
    val formattedDateTime = DateTimeFormatter
        .ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)

    val distanceKm = distanceMeters / 1000.0

    return RunUi(
        id = id!!,
        duration = duration.formatted(),
        dateTime = formattedDateTime,
        distance = distanceKm.toFormattedKm(),
        avgSpeed = avgSpeedKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl
    )
}