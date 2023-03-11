package com.example.foods.data.local

import com.example.foods.data.local.entities.FoodRecipeItemEntity
import com.example.foods.domain.local.FoodRecipesLocalDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesLocalDataSourceImpl @Inject constructor(
    private val foodRecipesDao: FoodRecipesDao
): FoodRecipesLocalDataSource {

    override fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable {
        return foodRecipesDao.insert(foodRecipes)
    }

    override fun getFoodRecipeById(id: Int): Single<FoodRecipeItemEntity> {
        return foodRecipesDao.getById(id)
    }
}
