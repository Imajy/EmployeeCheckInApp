package com.abs.technology.presentation.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abs.technology.presentation.common.text.text
import com.abs.technology.ui.theme.grayColor

@Composable
fun RoundedCornerButton(
    modifier: Modifier = Modifier,
    label: String,
    fontSize: Int,
    background : Color = grayColor.copy(.7f),
    color: Color = Color.White,
    enabled: Boolean = false,
    onClick: (Boolean) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onClick(false)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (enabled) {
                    onClick(true)
                }
            }
            .background(background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        text(
            value = label,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            lineHeight = 20,
            color = color
        )
    }
}