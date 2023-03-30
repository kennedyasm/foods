package com.example.foods.ui.common


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foods.core.design.R
import com.example.foods.core.ui.R as UiR

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetryClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.ic_error_outline),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = errorMessage,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        onRetryClick?.let {
            TextButton(
                onClick = it,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .testTag("error retry button")
            ) {
                Text(text = stringResource(id = UiR.string.retry_label))
            }
        }
    }
}
