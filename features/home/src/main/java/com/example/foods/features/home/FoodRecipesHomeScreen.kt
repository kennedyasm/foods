package com.example.foods.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.domain.State
import com.example.domain.State.Companion.to
import com.example.domain.models.FoodRecipeItemUi
import com.example.foods.core.design.R
import com.example.foods.ui.common.ImageIcon
import com.example.foods.ui.common.CircleProgress
import com.example.foods.ui.common.ErrorScreen

@Composable
fun FoodRecipesHomeScreen(
    viewModel: FoodRecipesViewModel = hiltViewModel(),
    navigateTo: (Int) -> Unit
) {

    val foodRecipesState by viewModel.foodRecipes.collectAsStateWithLifecycle()
    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val isSearching = viewModel.isSearching.collectAsStateWithLifecycle()
    var hasSearchFocus by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    when (val state = foodRecipesState) {
        is State.Success -> {
            HomeScreenView(
                foodRecipeList = state.to(),
                searchText = searchText.value,
                hasSearchFocus = hasSearchFocus,
                onValueChange = viewModel::onSearchTextChanged,
                onClearFocus = { focusManager.clearFocus() },
                isSearching = isSearching.value,
                onSearchViewHasFocus = {
                    hasSearchFocus = it
                    viewModel.hasSearchFocus(it)
                }
            ) {
                navigateTo.invoke(it)
            }
        }
        is State.Loading -> CircleProgress()
        is State.Error -> ErrorScreen(state.message) {
            viewModel.refreshFoodRecipes()
        }
    }


}

@Composable
fun HomeScreenView(
    foodRecipeList: List<FoodRecipeItemUi>,
    searchText: String,
    isSearching: Boolean,
    hasSearchFocus: Boolean,
    onValueChange: (String) -> Unit,
    onClearFocus: () -> Unit,
    onSearchViewHasFocus: (Boolean) -> Unit,
    onFoodRecipeItemClick: (Int) -> Unit,
) {

    val state = rememberLazyGridState()

    Surface {

        Column {

            Row(modifier = Modifier.height(IntrinsicSize.Min)) {

                OutlinedTextField(
                    value = searchText, onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                        .onFocusChanged {
                            onSearchViewHasFocus.invoke(it.hasFocus)
                        }
                        .semantics {
                            contentDescription = "search view"
                        }
                        .weight(.9f),
                    placeholder = { Text(text = "Buscar receta") },
                    leadingIcon = {
                        if (hasSearchFocus) {
                            IconButton(onClick = {
                                onValueChange.invoke("")
                                onClearFocus.invoke()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.mipmap.ic_back),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else {
                            Icon(
                                painter = painterResource(id = R.mipmap.ic_search),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                    },
                    trailingIcon = {

                        if (searchText.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    onValueChange.invoke("")
                                    onClearFocus.invoke()

                                },
                                modifier = Modifier.size(16.dp),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.mipmap.ic_close),
                                    contentDescription = null,
                                )
                            }
                        }

                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        onValueChange.invoke(searchText)
                        onClearFocus.invoke()
                    }),
                    shape = RoundedCornerShape(40.dp)
                )
                if (isSearching) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }


            }
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

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRecipeItemCard(item: FoodRecipeItemUi, onFoodRecipeItemClick: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        onClick = { onFoodRecipeItemClick.invoke(item.id) }
    ) {
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
            is AsyncImagePainter.State.Error -> ImageIcon(R.mipmap.ic_broken_image)
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
