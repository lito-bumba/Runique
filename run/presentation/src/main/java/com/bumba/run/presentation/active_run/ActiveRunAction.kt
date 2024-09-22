package com.bumba.run.presentation.active_run

sealed interface ActiveRunAction {

    data object OnToggleRunClick: ActiveRunAction

    data object OnFinishRunClick: ActiveRunAction

    data object OnResumeRunClick: ActiveRunAction

    data object OnBackRunClick: ActiveRunAction

    data class SubmitLocationPermissionInfo(
        val acceptedLocationPermission: Boolean,
        val showLocationRationale: Boolean
    ): ActiveRunAction

    data class SubmitNotificationPermissionInfo(
        val acceptedNotificationPermission: Boolean,
        val showNotificationRationale: Boolean
    ): ActiveRunAction

    data object OnDismissRationaleDialog: ActiveRunAction
}