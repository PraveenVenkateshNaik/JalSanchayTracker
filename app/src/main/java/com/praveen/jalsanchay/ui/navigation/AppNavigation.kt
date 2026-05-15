package com.praveen.jalsanchay.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.praveen.jalsanchay.ui.screens.entry.DataEntryScreen
import com.praveen.jalsanchay.ui.screens.history.HistoryScreen
import com.praveen.jalsanchay.ui.screens.home.HomeDashboardScreen
import com.praveen.jalsanchay.ui.screens.login.LoginScreen
import com.praveen.jalsanchay.ui.screens.onboarding.OnboardingScreen
import com.praveen.jalsanchay.ui.screens.profile.ProfileScreen
import com.praveen.jalsanchay.ui.screens.reports.ReportsScreen
import com.praveen.jalsanchay.ui.screens.splash.SplashScreen
import com.praveen.jalsanchay.ui.screens.tips.TipsScreen
import com.praveen.jalsanchay.ui.theme.DarkBlue
import com.praveen.jalsanchay.ui.theme.NeonCyan

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    object Splash : Screen("splash", "Splash")
    object Onboarding : Screen("onboarding", "Onboarding")
    object Login : Screen("login", "Login")
    
    // Bottom Nav Screens
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Reports : Screen("reports", "Reports", Icons.Default.PieChart)
    object Tips : Screen("tips", "Tips", Icons.Default.Info)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)

    // Other screens
    object DataEntry : Screen("data_entry", "Add Entry")
    object History : Screen("history", "History")
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Reports,
    Screen.Tips,
    Screen.Profile
)

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomNav = bottomNavItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                NavigationBar(
                    containerColor = DarkBlue,
                    contentColor = NeonCyan
                ) {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon!!, contentDescription = screen.title) },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DarkBlue,
                                selectedTextColor = NeonCyan,
                                indicatorColor = NeonCyan,
                                unselectedIconColor = NeonCyan.copy(alpha = 0.5f),
                                unselectedTextColor = NeonCyan.copy(alpha = 0.5f)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(onNavigateToNext = {
                    // Logic to check onboarding/login state could go here.
                    // For simplicity, we just route to onboarding.
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Onboarding.route) {
                OnboardingScreen(onFinishOnboarding = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Home.route) {
                HomeDashboardScreen(
                    onNavigateToEntry = { navController.navigate(Screen.DataEntry.route) },
                    onNavigateToHistory = { navController.navigate(Screen.History.route) }
                )
            }
            composable(Screen.DataEntry.route) {
                DataEntryScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.History.route) {
                HistoryScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.Reports.route) {
                ReportsScreen()
            }
            composable(Screen.Tips.route) {
                TipsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                })
            }
        }
    }
}
