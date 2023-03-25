package com.example.presentation.home

/*
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(foodRecipeList: List<FoodRecipeItemUi>){

    val state = rememberLazyGridState()


    LazyVerticalGrid(
    columns = GridCells.Adaptive(300.dp),
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(24.dp),
    modifier = Modifier
    .fillMaxSize()
    .testTag("forYou:feed"),
    state = state,
    ) {
        items(foodRecipeList) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {


                Row(modifier = Modifier.padding(all = 8.dp)) {
                    AsyncImage(
                        model = it.imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = " ${it.name}")
                }
            }
        }
    }
}

 */