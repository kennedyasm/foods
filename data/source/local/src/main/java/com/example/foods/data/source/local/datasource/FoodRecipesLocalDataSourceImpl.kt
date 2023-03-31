package com.example.foods.data.source.local.datasource

import com.example.foods.data.source.local.database.dao.FoodRecipesDao
import com.example.foods.data.source.local.database.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class FoodRecipesLocalDataSourceImpl @Inject constructor(
    private val foodRecipesDao: FoodRecipesDao
) : FoodRecipesLocalDataSource {

    override fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable =
        foodRecipesDao.insert(foodRecipes)

    override suspend fun getFoodRecipes(): List<FoodRecipeItemEntity> = foodRecipesDao.getAll()

    override suspend fun getFoodRecipeById(id: Int): FoodRecipeItemEntity =
        foodRecipesDao.getById(id)

    override fun deleteFoodRecipes(): Completable = foodRecipesDao.delete()
}
