package com.mamabe.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foods.ui.AppIcons.loginFoodIcon

@Composable
fun FoodRecipeLoginScreen(navigateTo: () -> Unit) {
    FoodRecipeLoginScreenView(navigateTo)
}

@Composable
fun FoodRecipeLoginScreenView(navigateTo: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        Image(
            imageVector = loginFoodIcon,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .size(80.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(value = "", onValueChange = {

        }, placeholder = {
            Text(text = "User")
        }, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = "", onValueChange = {

        }, placeholder = {
            Text(text = "Password")
        }, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navigateTo.invoke()
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Iniciar sesión")
        }
    }
}
