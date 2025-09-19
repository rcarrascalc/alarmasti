package com.uniandes.alarmasti.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf("Alarmas", "Tareas", "Reuniones")
    val icons = listOf(Icons.Default.AccessAlarm, Icons.Default.List, Icons.Default.Event)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(items[selectedItem]) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFD2CEF2) // color header
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFD2CEF2)) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción para agregar */ },
                containerColor = Color(0xFF4A3CFF)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", tint = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            if (selectedItem == 0) {
                // Alarmas
                AlarmCard("09:00 AM", "Levantarse")
                Spacer(modifier = Modifier.height(16.dp))
                AlarmCard("12:00 AM", "Tareas diarias")
            } else if (selectedItem == 1) {
                // Tareas
                Text("Aquí irán las Tareas", modifier = Modifier.padding(16.dp))
            } else {
                // Reuniones
                Text("Aquí irán las Reuniones", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun AlarmCard(time: String, description: String) {
    var checked by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD2CEF2)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(time, fontWeight = FontWeight.Bold)
                Text(description)
            }
            Switch(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF4A3CFF)
                )
            )
        }
    }
}
