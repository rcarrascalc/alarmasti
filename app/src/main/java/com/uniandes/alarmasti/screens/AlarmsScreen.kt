package com.uniandes.alarmasti.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uniandes.alarmasti.navigation.AlarmNavScreen
import com.uniandes.alarmasti.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alarmas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFCBCAE9),
                    titleContentColor = Color.Black
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("login") {

                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Salir",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AlarmNavScreen.CreateAlarm.route) },
                containerColor = Color(0xFF4A3CFF),
                shape = RoundedCornerShape(50)
            ) {
                Text("+", color = Color.White, style = MaterialTheme.typography.titleLarge)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            AlarmItem("09:00 AM", "Levantarse", navController)
            Spacer(modifier = Modifier.height(20.dp))
            AlarmItem("12:00 AM", "Tareas diarias", navController)
        }
    }
}


@Composable
fun AlarmItem(
    time: String,
    label: String,
    navController: NavHostController
) {
    var isEnabled by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(AlarmNavScreen.CreateAlarm.route) }, // 👉 Navegar al crear alarma
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0CFEA))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = time,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Switch(
                checked = isEnabled,
                onCheckedChange = { isEnabled = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF4A3CFF)
                )
            )
        }
    }
}

