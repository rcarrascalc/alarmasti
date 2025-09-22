package com.uniandes.alarmasti.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CardData(val title: String, val time: String)

@Composable
fun CardItem(data: CardData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFC9C5EC)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Avatar con inicial
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF4A3AFF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = data.title.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(text = data.time, fontSize = 14.sp)
            }

            Button(
                onClick = { /* TODO acción ver */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A3AFF),
                    contentColor = Color.White
                ),
                shape = CircleShape
            ) {
                Text("Ver")
            }
        }
    }
}
