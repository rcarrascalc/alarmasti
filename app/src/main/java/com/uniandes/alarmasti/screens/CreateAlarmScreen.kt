package com.uniandes.alarmasti.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uniandes.alarmasti.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAlarmScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var hour by remember { mutableStateOf("20") }
    var minute by remember { mutableStateOf("00") }
    var second by remember { mutableStateOf("00") }
    var isAm by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Alarma") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFCBCAE9),
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Seleccione hora",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            // Selector de hora
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                TimeBox(hour, "Horas")
                TimeBox(minute, "Minutos")
                TimeBox(second, "Segundos")

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Button(
                        onClick = { isAm = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isAm) Color(0xFFCBCAE9) else Color.LightGray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.width(70.dp)
                    ) {
                        Text("AM", color = Color.Black)
                    }
                    Button(
                        onClick = { isAm = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!isAm) Color(0xFFCBCAE9) else Color.LightGray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.width(70.dp)
                    ) {
                        Text("PM", color = Color.Black)
                    }
                }
            }

            // Nombre de la alarma
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre de la alarma") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f)) // Empuja el botón hacia abajo

            // Botón Guardar
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A3CFF))
            ) {
                Text("Guardar", color = Color.White, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun TimeBox(value: String, label: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCBCAE9)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.size(width = 80.dp, height = 100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        }
    }
}
