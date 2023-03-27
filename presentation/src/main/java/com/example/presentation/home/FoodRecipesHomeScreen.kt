package com.example.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.common.design.R
import com.example.domain.models.FoodRecipeItemUi

@Composable
fun HomeScreenView(foodRecipeList: List<FoodRecipeItemUi>, onFoodRecipeItemClick: (Int) -> Unit) {

    val state = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .testTag("home:recipes"),
        state = state,
    ) {
        items(foodRecipeList) {
            FoodRecipeItemCard(it, onFoodRecipeItemClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRecipeItemCard(item: FoodRecipeItemUi, onFoodRecipeItemClick: (Int) -> Unit) {
    Card(onClick = {
        onFoodRecipeItemClick.invoke(item.id)
    }) {
        Column {
            LoadFoodRecipeImage(item.imageUrl)
            FoodRecipeTitleText(item.name)
        }
    }
}

@Composable
fun LoadFoodRecipeImage(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "food recipe image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> BrokenImageIcon()
            else -> SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun FoodRecipeTitleText(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),

        )
}

@Composable
fun BrokenImageIcon() {
    Icon(
        painter = painterResource(id = R.mipmap.ic_broken_image),
        contentDescription = null
    )
}
