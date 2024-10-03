package com.abs.technology.presentation.loginScreen

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.abs.technology.MainActivity
import com.abs.technology.R
import com.abs.technology.common.navigation.Screen
import com.abs.technology.presentation.common.button.RoundedCornerButton
import com.abs.technology.presentation.common.textField.AddressInput
import com.abs.technology.ui.theme.blueColor
import com.abs.technology.ui.theme.grayColor
import com.abs.technology.ui.theme.whiteColor

@Preview
@Composable
private fun LoginScreenPrivie() {
    LoginScreen(navController = NavHostController(LocalContext.current), context = LocalContext.current as MainActivity)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(navController: NavHostController, context: MainActivity) {
    val height = LocalConfiguration.current.screenHeightDp.dp

    var backclick by remember { mutableStateOf(1) }

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val bringIntoViewRequester = BringIntoViewRequester()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(whiteColor)
            .padding(10.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        val backDispatcher =
            LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        DisposableEffect(backDispatcher) {
            val backCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (backclick > 0){
                        backclick--
                        Toast.makeText(context,"Please click back again to exit", Toast.LENGTH_SHORT).show()
                    }else{
                        context.finishAffinity()
                    }
                }
            }
            backDispatcher!!.addCallback(backCallback)
            onDispose {
                backCallback.remove()
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(.6f),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(15.dp))
        AddressInput(
            data = userName,
            placeHolder = "enter your username",
            label = "UserName",
            onValueChange = {
                userName = it
            },
            limit = 30,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        AddressInput(
            data = password,
            placeHolder = "enter your password",
            label = "Password",
            onValueChange = {
                password = it
            },
            limit = 30,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(15.dp))
        RoundedCornerButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(height / 15)
                .bringIntoViewRequester(bringIntoViewRequester),
            label = "Login",
            fontSize = 16,
            color = whiteColor,
            background = if (userName.isNotBlank() && password.isNotBlank()) blueColor else grayColor.copy(.5f),
            enabled = userName.isNotBlank() && password.isNotBlank(),
        ) {
            if (it) {
                navController.navigate(Screen.AuthOtpScreen.route){popUpTo(0)}
            }
        }
    }
}