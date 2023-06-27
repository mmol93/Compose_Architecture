package com.example.compose_architecture.ui.screen.views.bottomNav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.compose_architecture.R

sealed class BottomMenu(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    var haveBadge: Boolean
) {
    object Profile : BottomMenu("profile", R.string.profile, Icons.Outlined.Person, true)
    object FriendsList : BottomMenu("friendslist", R.string.friends_list, Icons.Outlined.List, true)
}