package com.bumba.analytics.data.di

import com.bumba.analytics.data.RoomAnalyticsRepository
import com.bumba.analytics.domain.AnalyticsRepository
import com.bumba.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}