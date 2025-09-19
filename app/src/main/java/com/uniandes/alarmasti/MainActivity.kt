package com.uniandes.alarmasti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniandes.alarmasti.screens.AlarmsScreen
import com.uniandes.alarmasti.screens.CreateAccountScreen
import com.uniandes.alarmasti.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") { LoginScreen(navController) }
                    composable("register") { CreateAccountScreen(navController)}
                    composable("alarms") { AlarmsScreen(navController) }

                }
            }
        }
    }
}
