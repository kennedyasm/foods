package com.mamabe.features.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foods.ui.AppIcons

@Composable
fun ProfileScreen(navigateToTakeProfilePicture: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        ) {
            Image(
                imageVector = AppIcons.userAccountIcon,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape),
                contentDescription = null,
            )

            IconButton(onClick = {
                navigateToTakeProfilePicture.invoke()
            }, content = {
                Image(
                    imageVector = AppIcons.editIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(6.dp)
                        .border(2.dp, Color.White, CircleShape)

                )
            }, modifier = Modifier.align(Alignment.BottomEnd))
        }


        Column(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Luis Alberto Salgado Marcial",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "lual.salma@gmail.com",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }
    }
}
