package com.bumba.auth.data.di

import com.bumba.auth.data.AuthRepositoryImpl
import com.bumba.auth.data.EmailPatternValidator
import com.bumba.auth.domain.AuthRepository
import com.bumba.auth.domain.PatternValidator
import com.bumba.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}