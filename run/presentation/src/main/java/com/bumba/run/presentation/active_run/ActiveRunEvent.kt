package com.bumba.run.presentation.active_run

import com.bumba.core.presentation.ui.UiText

sealed interface ActiveRunEvent {

    data class Error(val error: UiText): ActiveRunEvent

    data object RunSaved: ActiveRunEvent
}