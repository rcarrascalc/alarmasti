package com.uniandes.alarmasti.navigation


sealed class RootScreen(val route: String) {
    object Login : RootScreen("login")
    object CreateAccount : RootScreen("create_account")
    object Main : RootScreen("main")
}
