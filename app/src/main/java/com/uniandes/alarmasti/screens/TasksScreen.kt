package com.uniandes.alarmasti.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uniandes.alarmasti.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tareas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFCBCAE9),
                    titleContentColor = Color.Black
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("login") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
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
                onClick = { /* TODO: Agregar tarea */ },
                containerColor = Color(0xFF4A3CFF) // Morado fuerte
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
            TaskItem("Crear pantalla de Alarma", "09:00 AM")
            Spacer(modifier = Modifier.height(20.dp))
            TaskItem("Crear servicios", "12:00 PM")
        }
    }
}

@Composable
fun TaskItem(title: String, time: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCBCAE9)) // Lila exacto
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Avatar redondo con inicial dinámica
            Surface(
                shape = CircleShape,
                color = Color(0xFF4A3CFF), // Morado fuerte
                modifier = Modifier.size(40.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = title.first().toString(), // Inicial de la tarea
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Info de la tarea
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(time, style = MaterialTheme.typography.bodyMedium)
            }

            // Botón "Ver"
            Button(
                onClick = { /* TODO: Acción ver tarea */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A3CFF)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Ver", color = Color.White)
            }
        }
    }
}
