package com.uniandes.alarmasti.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val label: String, val icon: ImageVector) {
    object Alarms : BottomNavScreen("alarms", "Alarmas", Icons.Default.AccessAlarm)
    object Tasks : BottomNavScreen("tasks", "Tareas", Icons.Default.List)
    object Meetings : BottomNavScreen("meetings", "Reuniones", Icons.Default.Event)

}
