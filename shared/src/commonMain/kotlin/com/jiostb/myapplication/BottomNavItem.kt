package com.jiostb.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, var icon: ImageVector, var screen_route:String){

    object Home : BottomNavItem("Home", Icons.Default.Home,"home")
    object MyNetwork: BottomNavItem("My Network",Icons.Default.Home,"my_network")
    object AddPost: BottomNavItem("Post",Icons.Default.Home,"add_post")
    object Notification: BottomNavItem("Notification",Icons.Default.Home,"notification")
    object Jobs: BottomNavItem("Jobs",Icons.Default.Home,"jobs")
}