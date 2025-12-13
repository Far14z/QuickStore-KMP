package com.quickstore.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator

class BottomBarScreen : Screen {
    @OptIn(ExperimentalVoyagerApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    it,
                    listOf(
                        HomeTab,
                        FavTab,
                        ProfileTab
                    )
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(it.current.options.title) }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val tabNavigator = LocalTabNavigator.current

                        NavigationBarItem(
                            selected = tabNavigator.current.key == HomeTab.key,
                            label = { Text(HomeTab.options.title) },
                            icon = { Icon(painter = HomeTab.options.icon!!, contentDescription = null) },
                            onClick = {
                                tabNavigator.current = HomeTab
                            }
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == FavTab.key,
                            label = { Text(FavTab.options.title) },
                            icon = { Icon(painter = FavTab.options.icon!!, contentDescription = null) },
                            onClick = {
                                tabNavigator.current = FavTab
                            }
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProfileTab.key,
                            label = { Text(ProfileTab.options.title) },
                            icon = { Icon(painter = ProfileTab.options.icon!!, contentDescription = null) },
                            onClick = {
                                tabNavigator.current = ProfileTab
                            }
                        )
                    }
                },
                content = {
                    CurrentTab()
                }
            )
        }
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
