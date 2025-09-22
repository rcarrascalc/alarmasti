package com.uniandes.alarmasti.navigation

sealed class AlarmNavScreen(val route: String) {
    object CreateAlarm : AlarmNavScreen("create_alarm")
}