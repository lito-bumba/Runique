@file:OptIn(ExperimentalMaterial3Api::class)

package com.bumba.core.presentation.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumba.core.presentation.designsystem.AnalyticsIcon
import com.bumba.core.presentation.designsystem.ArrowLeftIcon
import com.bumba.core.presentation.designsystem.LogoIcon
import com.bumba.core.presentation.designsystem.Poppins
import com.bumba.core.presentation.designsystem.R
import com.bumba.core.presentation.designsystem.RuniqueGreen
import com.bumba.core.presentation.designsystem.RuniqueTheme
import com.bumba.core.presentation.designsystem.components.util.DropdownItem

@Composable
fun RuniqueToolbar(
    showBackButton: Boolean = true,
    title: String,
    modifier: Modifier = Modifier,
    menuItems: List<DropdownItem> = emptyList(),
    onMenuItemClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null
) {
    var isDropDownOpen by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                startContent?.invoke()
                Spacer(Modifier.width(8.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            if (showBackButton) {
                Icon(
                    imageVector = ArrowLeftIcon,
                    contentDescription = stringResource(R.string.go_back),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {
            if (menuItems.isNotEmpty()) {
                Box {
                    DropdownMenu(
                        expanded = isDropDownOpen,
                        onDismissRequest = {
                            isDropDownOpen = false
                        }
                    ) {
                        menuItems.forEachIndexed { index, item ->
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { onMenuItemClick(index) }
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ){
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = item.title)
                            }
                        }
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.open_menu),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun RuniqueToolbarPreview() {
    RuniqueTheme {
        RuniqueToolbar(
            showBackButton = false,
            title = "Runique",
            modifier = Modifier.fillMaxWidth(),
            startContent = {
                Icon(
                    imageVector = LogoIcon,
                    contentDescription = null,
                    tint = RuniqueGreen,
                    modifier = Modifier.size(35.dp)
                )
            },
            menuItems = listOf(
                DropdownItem(
                    icon = AnalyticsIcon,
                    title = "Analytics"
                )
            )
        )
    }
}