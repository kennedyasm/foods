package com.example.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.domain.State
import com.example.domain.State.Companion.to
import com.example.domain.models.FoodRecipeDetailsUi
import com.example.presentation.Screen
import com.example.presentation.common.CircleProgress
import com.example.presentation.common.ErrorScreen
import com.example.presentation.common.VerticalSpace
import com.example.presentation.home.BrokenImageIcon
import com.example.presentation.viewmodel.FoodRecipeDetailsViewModel

@Composable
fun FoodRecipeDetailsScreen(
    viewModel: FoodRecipeDetailsViewModel = hiltViewModel(),
    navigateTo: (Screen, Int) -> Unit
) {

    val composableState = viewModel.foodRecipeDetails.collectAsStateWithLifecycle()

    when (val state = composableState.value) {
        is State.Success -> FoodRecipeDetailsScreenView(state.to(), navigateTo)
        is State.Loading -> CircleProgress()
        is State.Error -> ErrorScreen(errorMessage = "Información no disponible")
    }
}



@Composable
fun FoodRecipeDetailsScreenView(item: FoodRecipeDetailsUi, navigateTo: (Screen, Int) -> Unit) {
    Surface {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            LazyColumn {

                item {
                    FoodRecipeMainDetails(item) {
                        navigateTo.invoke(Screen.DetailsMap, item.id)
                    }
                }

                item { FoodRecipeDetailsTitleDescription("Ingredientes:") }
                this.addIngredients(item.ingredients)

                item { FoodRecipeDetailsTitleDescription("Pasos para preparar:") }
                this.addPreparationSteps(item.preparation)
            }
        }
    }
}

@Composable
fun FoodRecipeMainDetails(item: FoodRecipeDetailsUi, navigateToMap: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Column {

            LoadFoodRecipeImageDetails(item.imageUrl)

            Column(modifier = Modifier.padding(16.dp)) {
                Text(item.title)
                VerticalSpace(16.dp)

                FoodRecipeDetailsOrigin(item.origin, navigateToMap)
                VerticalSpace(12.dp)

                Text(item.description)
                VerticalSpace(12.dp)
            }

        }
    }
}

@Composable
fun FoodRecipeDetailsOrigin(origin: String, navigateToMap: () -> Unit) {
    Row {
        Text("Origen: $origin", textAlign = TextAlign.Center)
        ClickableText(
            text = AnnotatedString("Ver en el mapa"),
            onClick = { navigateToMap.invoke() },
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}


@Composable
fun LoadFoodRecipeImageDetails(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "food recipe image",
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> BrokenImageIcon()
            else -> SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun FoodRecipeDetailsTitleDescription(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

fun LazyListScope.addIngredients(ingredients: List<String>) {
    items(ingredients) { FoodRecipeItemDetailText(it) }
}

fun LazyListScope.addPreparationSteps(steps: List<String>) {
    items(steps) { FoodRecipeItemDetailText(it) }
}

@Composable
fun FoodRecipeItemDetailText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    )
}

