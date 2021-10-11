package com.endrjudev.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.endrjudev.presentation.components.DetailsScreen
import com.endrjudev.presentation.components.MainScreen
import com.endrjudev.presentation.theme.GithubApiDemoTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubApiDemoTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()

                val navIcon: (@Composable () -> Unit)? =
                    if (backStackEntry.value?.destination?.route != "main") {
                        {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    painter = rememberVectorPainter(
                                        image = Icons.Default.ArrowBack
                                    ),
                                    contentDescription = null,
                                )
                            }
                        }
                    } else {
                        null
                    }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Repository API demo") },
                            navigationIcon = navIcon,
                            contentColor = MaterialTheme.colors.onSurface
                        )
                    }
                ) {

                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen { navController.navigate("detail/${it}") }
                        }
                        composable(
                            "detail/{repo}",
                            arguments = listOf(
                                navArgument("repo") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            DetailsScreen(itemId = it.arguments?.getInt("repo") ?: 0)
                        }
                    }
                }
            }
        }
    }
}