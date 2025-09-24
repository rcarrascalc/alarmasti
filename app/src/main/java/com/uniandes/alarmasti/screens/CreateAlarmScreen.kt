package com.uniandes.alarmasti.screens

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                title = { Text("Alarma") },
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
            Spacer(modifier = Modifier.height(36.dp))
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
    var hourText by remember { mutableStateOf(if (hour % 12 == 0) "12" else (hour % 12).toString()) }
    var minuteText by remember { mutableStateOf(minute.toString().padStart(2, '0')) }
    var secondText by remember { mutableStateOf(second.toString().padStart(2, '0')) }

    LaunchedEffect(hour) { hourText = if (hour % 12 == 0) "12" else (hour % 12).toString() }
    LaunchedEffect(minute) { minuteText = minute.toString().padStart(2, '0') }
    LaunchedEffect(second) { secondText = second.toString().padStart(2, '0') }

    fun displayHourTo24(displayHour: Int, am: Boolean): Int {
        return if (am) {
            if (displayHour == 12) 0 else displayHour
        } else {
            if (displayHour == 12) 12 else displayHour + 12
        }
    }

    @Composable
    fun TimeSquareField(
        text: String,
        onTextChange: (String) -> Unit
    ) {
        TextField(
            value = text,
            onValueChange = { new ->
                if (new.length <= 2 && (new.isEmpty() || new.all { it.isDigit() })) {
                    onTextChange(new)
                }
            },
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            singleLine = true,
            modifier = Modifier.size(80.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFCBCAE9),
                unfocusedContainerColor = Color(0xFFCBCAE9),
                disabledContainerColor = Color(0xFFCBCAE9),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {}
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Hora
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TimeSquareField(text = hourText, onTextChange = { new ->
                hourText = new
                val parsed = new.toIntOrNull()
                if (parsed != null && parsed in 1..12) {
                    val h24 = displayHourTo24(parsed, isAm)
                    onTimeChange(h24, minute, second)
                }
            })
            Text("Horas", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        }

        Text(
            ":",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        // Minutos
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TimeSquareField(text = minuteText, onTextChange = { new ->
                minuteText = new
                val parsed = new.toIntOrNull()
                if (parsed != null && parsed in 0..59) {
                    onTimeChange(hour, parsed, second)
                }
            })
            Text("Minutos", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        }

        Text(
            ":",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp)
        )


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TimeSquareField(text = secondText, onTextChange = { new ->
                secondText = new
                val parsed = new.toIntOrNull()
                if (parsed != null && parsed in 0..59) {
                    onTimeChange(hour, minute, parsed)
                }
            })
            Text("Segundos", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
        }

        Spacer(Modifier.width(12.dp))

        AmPmSelector(isAm = isAm, onAmPmChange = onAmPmChange)
    }
}


@Composable
fun AmPmSelector(
    isAm: Boolean,
    onAmPmChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .border(2.dp, Color(0xFF393B40), shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFFFEFEE))
                .clickable { onAmPmChange(true) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "AM",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF781E19)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFCBCAE9))
                .clickable { onAmPmChange(false) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "PM",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF393B40)
            )
        }
    }
}


