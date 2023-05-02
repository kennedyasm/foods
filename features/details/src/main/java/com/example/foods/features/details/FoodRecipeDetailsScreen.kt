package com.example.foods.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.foods.domain.State
import com.example.foods.domain.State.Companion.to
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.ui.AppIcons
import com.example.foods.ui.common.CircleProgress
import com.example.foods.ui.common.ErrorScreen

@Composable
fun FoodRecipeDetailsScreen(
    viewModel: FoodRecipeDetailsViewModel = hiltViewModel(),
    navigateToLocation: (Int) -> Unit,
    navigateToRecord: () -> Unit
) {

    val composableState = viewModel.foodRecipeDetails.collectAsStateWithLifecycle()

    when (val state = composableState.value) {
        is State.Success -> FoodRecipeDetailsScreenView(
            state.to(),
            navigateToLocation,
            navigateToRecord
        )
        is State.Loading -> CircleProgress()
        is State.Error -> ErrorScreen(state.message)
    }
}

@Composable
fun FoodRecipeDetailsScreenView(
    item: FoodRecipeDetailsUi,
    navigateToLocation: (Int) -> Unit,
    navigateToRecord: () -> Unit
) {
    Surface {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            LazyColumn(modifier = Modifier.testTag("food description container")) {

                item {
                    FoodRecipeMainDetails(
                        item,
                        { navigateToLocation.invoke(item.id) },
                        navigateToRecord
                    )
                }

                item { FoodRecipeDetailsTitleDescription(stringResource(id = R.string.ingredients_label)) }
                addIngredients(item.ingredients)

                item {
                    FoodRecipeDetailsTitleDescription(
                        stringResource(id = R.string.preparation_steps_label),
                        modifier = Modifier.testTag("steps to cook")
                    )
                }
                addPreparationSteps(item.preparation)
            }
        }
    }
}

@Composable
fun FoodRecipeMainDetails(
    item: FoodRecipeDetailsUi,
    navigateToLocation: () -> Unit,
    navigateToRecord: () -> Unit
) {
    Card(modifier = Modifier.padding(top = 12.dp)) {
        Column {

            LoadFoodRecipeImageDetails(item.imageUrl)

            Column(modifier = Modifier.padding(16.dp)) {
                Text(item.title)
                Spacer(modifier = Modifier.height(16.dp))

                FoodRecipeDetailsOrigin(item.origin, navigateToLocation, navigateToRecord)
                Spacer(modifier = Modifier.height(12.dp))

                Text(item.description)
                Spacer(modifier = Modifier.height(12.dp))
            }

        }
    }
}

@Composable
fun FoodRecipeDetailsOrigin(
    origin: String,
    navigateToMap: () -> Unit,
    navigateToRecord: () -> Unit
) {
    Row {
        Text(stringResource(id = R.string.origin, origin), textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterVertically))

        IconButton(
            onClick = {
                navigateToMap.invoke()
            },
            modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = AppIcons.worldIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        IconButton(
            onClick = {
                navigateToRecord.invoke()
            },
            modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = AppIcons.videoCameraIcon,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun LoadFoodRecipeImageDetails(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> AppIcons.brokenImageIcon
            else -> SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun FoodRecipeDetailsTitleDescription(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.padding(8.dp),
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
