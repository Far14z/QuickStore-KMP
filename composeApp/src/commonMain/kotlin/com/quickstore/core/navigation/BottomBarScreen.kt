package com.quickstore.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class BottomBarScreen : Screen {
    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }

}

class MainScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigator.push(SecondScreen())
                }
            ) {
                Text("Navigation to Second Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navigator.push(BottomBarScreen())
                }
            ) {
                Text("Bottom Bar")
            }
        }
    }
}

class SecondScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column (
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Second Screen", fontSize = 26.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navigator.pop()
                }
            ) {
                Text("Return")
            }
        }
    }
}
