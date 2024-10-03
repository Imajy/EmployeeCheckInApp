package com.abs.technology.presentation.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abs.technology.R
import com.abs.technology.common.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect (Unit){
        delay(1000)
        navController.navigate(Screen.LoginScreen.route){popUpTo(0)}
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image(
           painter = painterResource(R.drawable.logo),
           contentDescription = "logo",
           modifier = Modifier
               .background(Color.White)
               .fillMaxWidth(.7f),
           contentScale = ContentScale.FillWidth,
       )
    }
}

@Preview
@Composable
private fun SplashPRev() {
    SplashScreen(rememberNavController())
}