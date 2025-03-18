@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.bumba.run.presentation.run_overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumba.core.presentation.designsystem.AnalyticsIcon
import com.bumba.core.presentation.designsystem.LogoIcon
import com.bumba.core.presentation.designsystem.LogoutIcon
import com.bumba.core.presentation.designsystem.RunIcon
import com.bumba.core.presentation.designsystem.RuniqueTheme
import com.bumba.core.presentation.designsystem.components.RuniqueFloatingActionButton
import com.bumba.core.presentation.designsystem.components.RuniqueScaffold
import com.bumba.core.presentation.designsystem.components.RuniqueToolbar
import com.bumba.core.presentation.designsystem.components.util.DropdownItem
import com.bumba.run.presentation.R
import com.bumba.run.presentation.run_overview.components.RunListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    viewModel: RunOverviewViewModel = koinViewModel()
) {
    RunOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                RunOverviewAction.OnAnalyticsClick -> onAnalyticsClick()
                RunOverviewAction.OnStartClick -> onStartRunClick()
                RunOverviewAction.OnLogoutClick -> onLogoutClick()
                else -> Unit
            }

            viewModel.onAction(action)
        }
    )
}

@Composable
fun RunOverviewScreen(
    state: RunOverviewState,
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    RuniqueScaffold(
        topAppBar = {
            RuniqueToolbar(
                showBackButton = false,
                title = stringResource(R.string.runique),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropdownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(R.string.analytics)
                    ),
                    DropdownItem(
                        icon = LogoutIcon,
                        title = stringResource(R.string.logout)
                    )
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            RuniqueFloatingActionButton(
                icon = RunIcon,
                onClick = {
                    onAction(RunOverviewAction.OnStartClick)
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = state.runs,
                key = { it.id }
            ) {
                RunListItem(
                    runUi = it,
                    onDeleteClick = {
                        onAction(RunOverviewAction.DeleteRun(it))
                    },
                    modifier = Modifier
                        .animateItemPlacement()
                )
            }
        }
    }
}

@Preview
@Composable
fun RunOverviewScreenPreview() {
    RuniqueTheme {
        RunOverviewScreen(
            state = RunOverviewState(),
            onAction = { }
        )
    }
}

