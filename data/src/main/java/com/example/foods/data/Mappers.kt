package com.example.foods.data

import com.example.foods.data.source.local.database.entities.FoodRecipeItemEntity
import com.example.foods.data.network.dto.FoodRecipeItemDto
import com.example.foods.data.network.dto.FoodRecipesResponseDto
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.models.FoodRecipeLocationMapUi

fun FoodRecipesResponseDto.toFoodRecipeItemUiList() =
    foodRecipes.map(FoodRecipeItemDto::toFoodRecipeItemUi)

fun FoodRecipeItemDto.toFoodRecipeItemUi() = FoodRecipeItemUi(id, name, imageUrl, ingredients)

fun FoodRecipesResponseDto.toFoodRecipeItemEntityList() =
    foodRecipes.map(FoodRecipeItemDto::toFoodRecipeItemEntity)

fun FoodRecipeItemDto.toFoodRecipeItemEntity() =
    FoodRecipeItemEntity(
        id,
        name,
        description,
        ingredients,
        preparation,
        imageUrl,
        origin,
        latitude,
        longitude
    )

fun FoodRecipeItemEntity.toFoodRecipeDetailsUi(): FoodRecipeDetailsUi =
    FoodRecipeDetailsUi(
        id,
        name,
        imageUrl,
        description,
        origin,
        ingredients,
        preparation
    )

fun FoodRecipeItemEntity.toFoodRecipeItemUi(): FoodRecipeItemUi =
    FoodRecipeItemUi(id, name, imageUrl, ingredients)

fun FoodRecipeItemEntity.toFoodRecipeMapDetailUi(): FoodRecipeLocationMapUi =
    FoodRecipeLocationMapUi(id, name, latitude, longitude)