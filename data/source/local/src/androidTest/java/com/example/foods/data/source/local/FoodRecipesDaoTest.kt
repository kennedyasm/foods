package com.example.foods.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.foods.data.source.local.asserts.assertFoodRecipeItemEntity
import com.example.foods.data.source.local.asserts.assertFoodRecipeItemEntityList
import com.example.foods.data.source.local.database.FoodRecipesRoomDatabase
import com.example.foods.data.source.local.database.dao.FoodRecipesDao
import com.example.foods.data.source.local.doubles.provideFoodRecipeItemEntityList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FoodRecipesDaoTest {

    private lateinit var foodRecipesDao: FoodRecipesDao
    private lateinit var database: FoodRecipesRoomDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, FoodRecipesRoomDatabase::class.java).build()
        foodRecipesDao = database.foodRecipesDao()
    }

    @Test
    fun insertFoodRecipesItemEntityList() {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()

        foodRecipesDao.insert(foodRecipeItemEntityList).test().assertNoErrors().assertComplete()
    }

    @Test
    fun getAllFoodRecipeItemEntityList() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        foodRecipesDao.insert(foodRecipeItemEntityList).test().assertNoErrors().assertComplete()

        val items = foodRecipesDao.getAll()

        assertFoodRecipeItemEntityList(items)
    }

    @Test
    fun getByIdFoodRecipeItemEntity() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        foodRecipesDao.insert(foodRecipeItemEntityList).test().assertNoErrors().assertComplete()

        val item = foodRecipesDao.getById(2)

        assertFoodRecipeItemEntity(item)
    }

    @Test
    fun deleteFoodRecipeItemEntities() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        foodRecipesDao.insert(foodRecipeItemEntityList).test().assertNoErrors().assertComplete()

        foodRecipesDao.delete().test().assertNoErrors().assertComplete()

        val items = foodRecipesDao.getAll()

        assertThat(items.isEmpty(), equalTo(true))
    }
}
