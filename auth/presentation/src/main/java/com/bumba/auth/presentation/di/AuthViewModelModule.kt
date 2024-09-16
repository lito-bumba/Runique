package com.bumba.auth.presentation.di

import com.bumba.auth.presentation.register.RegisterViewModel
import com.bumba.auth.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}