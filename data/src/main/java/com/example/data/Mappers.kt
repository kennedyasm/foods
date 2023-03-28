package com.example.data

import com.example.data.local.entities.FoodRecipeItemEntity
import com.example.data.network.dto.FoodRecipeItemDto
import com.example.data.network.dto.FoodRecipesResponseDto
import com.example.domain.models.FoodRecipeDetailsUi
import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.models.FoodRecipeMapDetailUi

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

fun FoodRecipeItemEntity.toFoodRecipeMapDetailUi(): FoodRecipeMapDetailUi =
    FoodRecipeMapDetailUi(id, name, latitude, longitude)