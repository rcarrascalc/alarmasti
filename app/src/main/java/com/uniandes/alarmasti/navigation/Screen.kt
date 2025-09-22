package com.uniandes.alarmasti.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String = "",
    val icon: ImageVector? = null
) {
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
    object Main : Screen("main")

    object Alarms : Screen("alarms", "Alarmas", Icons.Filled.AccessAlarm)
    object Tasks : Screen("tasks", "Tareas", Icons.Filled.List)
    object Meetings : Screen("meetings", "Reuniones", Icons.Filled.People)
}
