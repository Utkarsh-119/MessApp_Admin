package com.ex.messreview_admin.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MessTypeScreen(navController: NavController, catererName: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
                    .copy(alpha = 0.6f)
                    .compositeOver(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "MESS-TYPE",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            MessTypeCard("VEG", 1, navController, catererName)
            Spacer(modifier = Modifier.height(16.dp))
            MessTypeCard("NON VEG", 2, navController, catererName)
            Spacer(modifier = Modifier.height(16.dp))
            MessTypeCard("SPECIAL", 3, navController, catererName)
        }
    }
}

@Composable
fun MessTypeCard(type: String, rating: Int, navController: NavController, catererName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Navigate to HomeScreen and pass both catererName and type
                navController.navigate("home_screen/$catererName/$type")
            },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(36.dp))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = type,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.weight(1f))

            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (i <= rating) Color.Yellow else Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessTypeScreenPreview() {MaterialTheme {
    MessTypeScreen(
        navController = rememberNavController(),
        catererName = "Sample Caterer" // Provide a sample caterer name
    )
}
}