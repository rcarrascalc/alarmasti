package com.uniandes.alarmasti.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uniandes.alarmasti.screens.CreateAccountScreen
import com.uniandes.alarmasti.screens.LoginScreen
import com.uniandes.alarmasti.screens.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Login.route
    ) {
        composable(RootScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(RootScreen.CreateAccount.route) {
            CreateAccountScreen(navController)
        }
        composable(RootScreen.Main.route) {
            MainScreen()
        }
    }
}
