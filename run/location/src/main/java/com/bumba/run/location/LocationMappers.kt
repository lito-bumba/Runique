package com.bumba.run.location

import android.location.Location
import com.bumba.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.bumba.core.domain.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )
}