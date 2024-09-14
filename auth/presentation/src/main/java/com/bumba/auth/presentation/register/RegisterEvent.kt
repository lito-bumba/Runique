package com.bumba.auth.presentation.register

import com.bumba.core.presentation.ui.UiText

sealed interface RegisterEvent {

    data object RegistrationSuccess : RegisterEvent

    data class Error(val error: UiText) : RegisterEvent

}