package com.example.data.local.datasource

import com.example.data.local.dao.FoodRecipesDao
import com.example.data.local.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesLocalDataSourceImpl @Inject constructor(
    private val foodRecipesDao: FoodRecipesDao
) : FoodRecipesLocalDataSource {

    override fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable =
        foodRecipesDao.insert(foodRecipes)

    override suspend fun getFoodRecipes(): List<FoodRecipeItemEntity> = foodRecipesDao.getAll()

    override fun getFoodRecipeById(id: Int): Single<FoodRecipeItemEntity> =
        foodRecipesDao.getById(id)

    override fun deleteFoodRecipes(): Completable = foodRecipesDao.delete()
}
