package com.bumba.auth.presentation.login

import com.bumba.core.presentation.ui.UiText

sealed interface LoginEvent {

    data class Error(val error: UiText): LoginEvent

    data object LoginSuccess: LoginEvent
}