package com.jiostb.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Sidebar(drawerState: DrawerState,  closeDrawer:()->Unit, content: @Composable () -> Unit,) {

    val showSubMenu = remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "my app",
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text ="Settings") },
                    icon = {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    selected = false,
                    onClick = { closeDrawer() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "list") },
                    icon = {
                        Icon(
                            Icons.Filled.List,
                            contentDescription = "list"
                        )
                    },
                    selected = false,
                    onClick = { showSubMenu.value = !showSubMenu.value }
                )
                AnimatedVisibility(visible = showSubMenu.value) {
                    SubMenu()
                }
            }
        },

        gesturesEnabled = true,
        content = content
    )
}

@Composable
fun SubMenu() {
    Column ( Modifier.padding(start = 16.dp, end = 24.dp, top = 15.dp)){
        Text(text = "Option 1")
        Text(text = "Option 2")
        Text(text = "Option 2")
    }
}