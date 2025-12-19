package com.quickstore.features.profiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class ProfileScreen : Screen {

    @Composable
    override fun Content() {

        var name by remember { mutableStateOf("") }
        var isVip by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isVip,
                    onCheckedChange = {
                        isVip = it
                    }
                )
                Text("Are you a VIP?", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {

                },
                enabled = name.isNotBlank()
            ) {
                Text("Save")
            }

            Spacer(modifier = Modifier.weight(0.3f))
        }
    }

}