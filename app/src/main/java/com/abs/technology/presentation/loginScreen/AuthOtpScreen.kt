package com.abs.technology.presentation.loginScreen

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abs.technology.R
import com.abs.technology.common.navigation.Screen
import com.abs.technology.presentation.common.button.RoundedCornerButton
import com.abs.technology.presentation.common.text.text
import com.abs.technology.presentation.common.textField.OtpTextField
import com.abs.technology.ui.theme.blackColor
import com.abs.technology.ui.theme.blueColor
import com.abs.technology.ui.theme.grayColor
import com.abs.technology.ui.theme.whiteColor

@Preview
@Composable
private fun AuthOtpScreenPreview() {
    AuthOtpScreen(rememberNavController())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthOtpScreen(navController: NavHostController) {

    val height = LocalConfiguration.current.screenHeightDp.dp

    var otpText by remember { mutableStateOf("") }

    val bringIntoViewRequester = BringIntoViewRequester()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(whiteColor)
    ) {
        val backDispatcher =
            LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        DisposableEffect(backDispatcher) {
            val backCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(Screen.LoginScreen.route) { popUpTo(0) }
                }
            }
            backDispatcher!!.addCallback(backCallback)
            onDispose {
                backCallback.remove()
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back arrow",
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { navController.navigate(Screen.LoginScreen.route) { popUpTo(0) } },
                tint = blackColor
            )
            text(
                value = "Google authenticator otp",
                fontWeight = FontWeight.Medium,
                fontSize = 16,
                color = blackColor
            )
        }
        Column(
            modifier = Modifier
                .background(whiteColor)
                .padding(10.dp)
                .background(whiteColor),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(height / 60))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(.6f),
                contentScale = ContentScale.FillWidth,
            )

            Spacer(modifier = Modifier.height(height / 60))
            text("Enter otp to continue :-", fontSize = 18, color = blackColor, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                OtpTextField(
                    otpText = otpText,
                    otpCount = 4
                ) { text, isFinished ->
                    otpText = text
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            RoundedCornerButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 15)
                    .bringIntoViewRequester(bringIntoViewRequester),
                label = "Submit",
                fontSize = 16,
                color = whiteColor ,
                background = if (otpText.length == 4) blueColor else grayColor.copy(.5f),
                enabled = otpText.isNotBlank()
            ) {
                if (it) {
                    navController.navigate(Screen.CheckInOut.route) { popUpTo(0) }
                }
            }
        }
    }
}
