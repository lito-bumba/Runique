package com.bumba.run.presentation.run_overview

import androidx.lifecycle.ViewModel

class RunOverviewViewModel: ViewModel() {

    fun onAction(action: RunOverviewAction) {
        when(action) {
            RunOverviewAction.OnAnalyticsClick -> Unit
            RunOverviewAction.OnLogoutClick -> Unit
            RunOverviewAction.OnStartClick -> Unit
        }
    }
}