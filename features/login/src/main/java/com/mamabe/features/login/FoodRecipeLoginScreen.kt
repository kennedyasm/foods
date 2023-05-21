package com.mamabe.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.foods.ui.AppIcons
import com.example.foods.ui.AppIcons.loginFoodIcon

@Composable
fun FoodRecipeLoginScreen(success: () -> Unit, navigateToSignIn: () -> Unit) {
    FoodRecipeLoginScreenView(success)
}

@Composable
fun FoodRecipeLoginScreenView(navigateTo: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Image(
            imageVector = loginFoodIcon, contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(80.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = "", onValueChange = {

            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(.8f)
                .padding(12.dp)
                .onFocusChanged {

                },
            placeholder = { Text(text = "E-mail") },
            leadingIcon = {
                Icon(imageVector = AppIcons.emailIcon, contentDescription = null)
            },
            keyboardOptions = emailKeyBoardOptions,
            keyboardActions = KeyboardActions(onSearch = {

            }),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = { },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(.8f)
                .padding(12.dp)
                .onFocusChanged {

                }
                .testTag("food recipes search"),
            placeholder = { Text(text = "Password") },
            leadingIcon = {
                Icon(imageVector = AppIcons.keyIcon, contentDescription = null)
            },

            keyboardOptions = passwordKeyBoardOptions,
            keyboardActions = KeyboardActions(onSearch = {

            }),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navigateTo.invoke()
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Iniciar sesión")
        }


        Spacer(modifier = Modifier.height(8.dp))

        ClickableText(
            text = AnnotatedString("Registrate"),
            onClick = {

            },
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

private val passwordKeyBoardOptions = KeyboardOptions(
    capitalization = KeyboardCapitalization.None,
    autoCorrect = true,
    keyboardType = KeyboardType.Password,
    imeAction = ImeAction.Done
)

private val emailKeyBoardOptions = KeyboardOptions(
    capitalization = KeyboardCapitalization.None,
    autoCorrect = true,
    keyboardType = KeyboardType.Email,
    imeAction = ImeAction.Next
)
