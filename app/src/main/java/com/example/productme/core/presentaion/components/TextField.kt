package com.example.productme.core.presentaion.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.curativepis.ui.theme.spacing

@Composable
fun DefaultTextField(
    value: String,
    label: String,
    onTextChange: (String) -> Unit = {},
    padding: PaddingValues = PaddingValues(horizontal = MaterialTheme.spacing.medium),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email
    ),
    isError: Boolean = false,
    isPasswordTextField: Boolean = false,
    TrailingIcon:ImageVector?
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        value = value, onValueChange = {
            onTextChange(it)
        }, label = { Text(text = label, style = MaterialTheme.typography.caption) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
            unfocusedBorderColor = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
        ),
        isError = isError,
        keyboardOptions = keyboardOptions,
        singleLine = true,

        trailingIcon = {
            if (isPasswordTextField) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }else{
                if (TrailingIcon != null) {
                    Icon(imageVector = TrailingIcon, contentDescription =null )
                }
            }
        },
        visualTransformation = if (isPasswordTextField) {
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )


}