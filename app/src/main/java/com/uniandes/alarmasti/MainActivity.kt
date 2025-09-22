package com.uniandes.alarmasti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniandes.alarmasti.navigation.RootScreen
import com.uniandes.alarmasti.screens.*
import com.uniandes.alarmasti.ui.theme.AlarmastiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmastiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

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
            }
        }
    }
}
