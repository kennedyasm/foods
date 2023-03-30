package com.example.foods.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.foods.domain.State
import com.example.foods.domain.State.Companion.to
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.ui.common.CircleProgress
import com.example.foods.ui.common.ErrorScreen
import com.example.foods.ui.common.ImageIcon
import com.example.foods.core.ui.R as UiR

@Composable
fun FoodRecipesHomeScreen(
    viewModel: FoodRecipesViewModel = hiltViewModel(), navigateToDetails: (Int) -> Unit
) {

    val foodRecipesState by viewModel.foodRecipes.collectAsStateWithLifecycle()
    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val isSearching = viewModel.isSearching.collectAsStateWithLifecycle()
    var hasSearchFocus by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    when (val state = foodRecipesState) {
        is State.Success -> {
            HomeScreenView(foodRecipeList = state.to(),
                searchText = searchText.value,
                hasSearchFocus = hasSearchFocus,
                onValueChange = viewModel::onSearchTextChanged,
                onClearFocus = { focusManager.clearFocus() },
                isSearching = isSearching.value,
                onSearchViewHasFocus = {
                    hasSearchFocus = it
                    viewModel.hasSearchFocus(it)
                }) { navigateToDetails.invoke(it) }
        }
        is State.Loading -> CircleProgress()
        is State.Error -> {
            ErrorScreen(
                stringResource(id = R.string.error, state.message)
            ) {
                viewModel.refreshFoodRecipes()
            }
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
            Column {
                SearchText(
                    searchText,
                    onValueChange,
                    onSearchViewHasFocus,
                    hasSearchFocus,
                    onClearFocus
                )
                if (isSearching) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            FoodRecipesList(foodRecipeList, state, onFoodRecipeItemClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRecipeItemCard(item: FoodRecipeItemUi, onFoodRecipeItemClick: (Int) -> Unit) {
    Card(onClick = { onFoodRecipeItemClick.invoke(item.id) }) {
        Column {
            LoadFoodRecipeImage(item.imageUrl)
            FoodRecipeTitleText(item.name)
        }
    }
}

@Composable
fun LoadFoodRecipeImage(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> ImageIcon(UiR.mipmap.ic_broken_image)
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
fun SearchIcon(iconResourceId: Int, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = iconResourceId),
        contentDescription = null,
        modifier = modifier.size(16.dp)
    )
}

@Composable
fun LeadingSearchIcon(hasSearchFocus: Boolean, onClick: () -> Unit) {

    if (hasSearchFocus) {
        IconButton(onClick = onClick, modifier = Modifier.testTag("search back button")) {
            SearchIcon(UiR.mipmap.ic_back)
        }
    } else {
        SearchIcon(UiR.mipmap.ic_search, modifier = Modifier.testTag("search icon"))
    }
}

@Composable
fun TrailingSearchIcon(searchText: String, onClick: () -> Unit) {
    if (searchText.isNotBlank()) {
        IconButton(onClick = onClick, modifier = Modifier.testTag("clean search text button")) {
            SearchIcon(UiR.mipmap.ic_close)
        }
    }
}

@Composable
fun SearchText(
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearchViewHasFocus: (Boolean) -> Unit,
    hasSearchFocus: Boolean,
    onClearFocus: () -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .onFocusChanged { onSearchViewHasFocus.invoke(it.hasFocus) }
            .testTag("food recipes search"),
        placeholder = { Text(text = stringResource(id = R.string.search_hint_label)) },
        leadingIcon = {
            LeadingSearchIcon(hasSearchFocus) {
                onClearFocus.invoke()
            }
        },
        trailingIcon = {
            TrailingSearchIcon(searchText) {
                onClearFocus.invoke()
                onValueChange.invoke("")
            }
        },
        keyboardOptions = searchKeyBoardOptions,
        keyboardActions = KeyboardActions(onSearch = {
            onClearFocus.invoke()
            onValueChange.invoke(searchText)
        }),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun FoodRecipesList(
    foodRecipeList: List<FoodRecipeItemUi>,
    state: LazyGridState,
    onFoodRecipeItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize(),
        state = state,
    ) {
        items(foodRecipeList) {
            FoodRecipeItemCard(it, onFoodRecipeItemClick)
        }
    }
}

private val searchKeyBoardOptions = KeyboardOptions(
    capitalization = KeyboardCapitalization.None,
    autoCorrect = true,
    keyboardType = KeyboardType.Text,
    imeAction = ImeAction.Search
)
