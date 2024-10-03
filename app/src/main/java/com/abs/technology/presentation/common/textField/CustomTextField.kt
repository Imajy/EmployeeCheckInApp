package com.abs.technology.presentation.common.textField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abs.technology.presentation.common.text.text
import com.abs.technology.ui.theme.blueColor
import com.abs.technology.ui.theme.blueColorFaded
import com.abs.technology.ui.theme.grayColor

@Composable
fun AddressInput(
    data: String,
    placeHolder: String = "",
    label: String = "",
    onValueChange: (String) -> Unit,
    limit: Int,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    )
) {
    Column {
        if (label.isNotBlank()) {
            text(
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                value = label,
                fontSize = 15,
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .clip(RoundedCornerShape(8.dp)),
            value = data,
            onValueChange = { if (it.length<limit) {onValueChange(it)} },
            singleLine = true,
            textStyle = TextStyle(color = grayColor, fontSize = 13.sp),
            placeholder = {
                Text(placeHolder, fontSize = 13.sp, color = grayColor)
            },
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.colors(
                focusedTextColor = blueColor,
                unfocusedTextColor = blueColor,
                focusedIndicatorColor = blueColor,
                unfocusedIndicatorColor = blueColor,
                focusedContainerColor = blueColorFaded,
                unfocusedContainerColor = blueColorFaded,
                cursorColor = blueColor,
            ),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                containerColor = blueColorFaded,
//                focusedBorderColor = Color.Gray.copy(alpha = 0.6f),
//                unfocusedBorderColor = Color.Gray.copy(alpha = 0.6f),
//                cursorColor = blueColor,
//                errorBorderColor = Color.Red
//            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}