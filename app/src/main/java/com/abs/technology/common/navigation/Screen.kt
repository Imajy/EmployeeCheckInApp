package com.abs.technology.common.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object AuthOtpScreen : Screen("auth_otp_screen")
    object CheckInOut : Screen("CheckInOut_screen")


}