package com.quickstore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.quickstore.core.di.platformModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {modules(
        modules = platformModule
    )}) {
        Box(
            Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center
        ) {
            Text("Hi")
        }
    }
}