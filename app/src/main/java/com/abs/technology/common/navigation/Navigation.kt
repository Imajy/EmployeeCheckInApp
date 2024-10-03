package com.abs.technology.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abs.technology.MainActivity
import com.abs.technology.presentation.home.CheckInOut
import com.abs.technology.presentation.loginScreen.AuthOtpScreen
import com.abs.technology.presentation.loginScreen.LoginScreen
import com.abs.technology.presentation.splashScreen.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    context: MainActivity
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController,context)
        }
        composable(Screen.AuthOtpScreen.route) {
            AuthOtpScreen(navController)
        }
        composable(Screen.CheckInOut.route) {
            CheckInOut()
        }
    }
}
