package com.bumba.run.presentation.active_run

sealed interface ActiveRunAction {

    data object OnToggleRunClick: ActiveRunAction

    data object OnFinishRunClick: ActiveRunAction

    data object OnResumeRunClick: ActiveRunAction

    data object OnBackRunClick: ActiveRunAction

    data object OnDismissRunClick: ActiveRunAction
}