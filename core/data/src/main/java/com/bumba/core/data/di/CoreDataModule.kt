package com.bumba.core.data.di

import com.bumba.core.data.auth.EncryptedSessionStorage
import com.bumba.core.data.networking.HttpClientFactory
import com.bumba.core.data.run.OfflineFirstRunRepository
import com.bumba.core.domain.SessionStorage
import com.bumba.core.domain.run.LocalRunDataSource
import com.bumba.core.domain.run.RemoteRunDataSource
import com.bumba.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}