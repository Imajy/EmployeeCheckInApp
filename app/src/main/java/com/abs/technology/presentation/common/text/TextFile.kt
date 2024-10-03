package com.abs.technology.presentation.common.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.abs.technology.ui.theme.blackColor

@Composable
fun text(
    value: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: Int = 11,
    lineHeight: Int = 14,
    maxLines: Int = 1,
    color: Color = blackColor,
    shadow: Color = Color.Transparent
) {
    Text(
        text = value,
        fontSize = fontSize.sp,
        modifier = modifier,
        textAlign = textAlign,
        lineHeight = lineHeight.sp,
        fontWeight = fontWeight,
        maxLines = maxLines,
        style = TextStyle(
            fontSize = fontSize.sp,
            color = color,
            shadow = Shadow(
                color = shadow,
                offset = Offset(2f, 2f),
                blurRadius = 4f
            )
        )
    )
}