package com.example.foods.common.extensions

import com.example.foods.data.local.entities.FoodRecipeItemEntity
import com.example.foods.domain.dto.FoodRecipeItemDTO
import com.example.foods.domain.network.FoodRecipeItemResponse
import com.example.foods.domain.network.FoodRecipesResponse

fun FoodRecipesResponse.toFoodRecipeItemDTOList() =
    foodRecipes.map(FoodRecipeItemResponse::toFoodRecipeItemDTO)

fun FoodRecipeItemResponse.toFoodRecipeItemDTO() = FoodRecipeItemDTO(id, name, imageUrl)

fun FoodRecipesResponse.toFoodRecipeItemEntityList() =
    foodRecipes.map(FoodRecipeItemResponse::toFoodRecipeItemEntity)

fun FoodRecipeItemResponse.toFoodRecipeItemEntity() =
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

fun FoodRecipeItemEntity.toFoodRecipeItemResponse(): FoodRecipeItemDTO =
    FoodRecipeItemDTO(id, name, imageUrl)