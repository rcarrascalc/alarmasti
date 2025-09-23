package com.uniandes.alarmasti.screens

import android.widget.NumberPicker
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.uniandes.alarmasti.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAlarmScreen(navController: NavController) {
    var hour by remember { mutableStateOf(20) }
    var minute by remember { mutableStateOf(0) }
    var second by remember { mutableStateOf(0) }
    var isAm by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf(TextFieldValue("")) }

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

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TimePickerWithAmPm(
                    hour = hour,
                    minute = minute,
                    second = second,
                    isAm = isAm,
                    onTimeChange = { h, m, s ->
                        hour = h
                        minute = m
                        second = s
                    },
                    onAmPmChange = { am ->
                        isAm = am
                    }
                )


            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre de la alarma") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

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
fun TimePickerWithAmPm(
    hour: Int,
    minute: Int,
    second: Int,
    isAm: Boolean,
    onTimeChange: (Int, Int, Int) -> Unit,
    onAmPmChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // === Hora ===
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AndroidView(
                factory = { context ->
                    NumberPicker(context).apply {
                        minValue = 1
                        maxValue = 12
                        value = if (hour % 12 == 0) 12 else hour % 12
                        setOnValueChangedListener { _, _, newVal ->
                            val adjustedHour = if (isAm) newVal % 12 else (newVal % 12) + 12
                            onTimeChange(adjustedHour, minute, second)
                        }
                    }
                },
                modifier = Modifier.width(80.dp).height(100.dp)
            )
            Text("Horas", style = MaterialTheme.typography.bodySmall)
        }

        // === Minutos ===
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AndroidView(
                factory = { context ->
                    NumberPicker(context).apply {
                        minValue = 0
                        maxValue = 59
                        value = minute
                        setFormatter { i -> String.format("%02d", i) }
                        setOnValueChangedListener { _, _, newVal ->
                            onTimeChange(hour, newVal, second)
                        }
                    }
                },
                modifier = Modifier.width(80.dp).height(100.dp)
            )
            Text("Minutos", style = MaterialTheme.typography.bodySmall)
        }

        // === Segundos ===
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AndroidView(
                factory = { context ->
                    NumberPicker(context).apply {
                        minValue = 0
                        maxValue = 59
                        value = second
                        setFormatter { i -> String.format("%02d", i) }
                        setOnValueChangedListener { _, _, newVal ->
                            onTimeChange(hour, minute, newVal)
                        }
                    }
                },
                modifier = Modifier.width(80.dp).height(100.dp)
            )
            Text("Segundos", style = MaterialTheme.typography.bodySmall)
        }

        // === AM/PM ===
        Column(
            modifier = Modifier
                .width(70.dp)
                .height(100.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onAmPmChange(true) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAm) Color(0xFFFFEBEE) else Color.LightGray
                ),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text("AM", color = if (isAm) Color.Red else Color.Black)
            }
            Button(
                onClick = { onAmPmChange(false) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isAm) Color(0xFFDDD6F3) else Color.LightGray
                ),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text("PM", color = if (!isAm) Color.DarkGray else Color.Black)
            }
        }
    }
}

