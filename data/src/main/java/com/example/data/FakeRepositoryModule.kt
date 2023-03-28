package com.example.data

import com.example.data.di.RepositoryModule
import com.example.data.repository.FoodRecipesRepositoryImpl
import com.example.domain.models.FoodRecipeDetailsUi
import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.models.FoodRecipeMapDetailUi
import com.example.domain.repository.FoodRecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Inject
/*
class FakeRepository @Inject constructor(): FoodRecipesRepository {
    override fun getFoodRecipes(): Single<List<FoodRecipeItemUi>> {
        return Single.just(emptyList<FoodRecipeItemUi>())
    }

    override suspend fun getFoodRecipeDetailsById(id: Int): FoodRecipeDetailsUi {
        return FoodRecipeDetailsUi(1,"","","","", emptyList(), emptyList())
    }

    override fun getFoodRecipesByQuery(query: String): Flow<List<FoodRecipeItemUi>> {
        return flow<List<FoodRecipeItemUi>> {
            delay(500)
            emit(emptyList())
        }.flowOn(UnconfinedTestDispatcher())
    }

    override fun deleteFoodRecipes(): Completable {
        return Completable.complete()
    }

    override suspend fun getFoodRecipeMapDetailById(id: Int): FoodRecipeMapDetailUi {
        return FoodRecipeMapDetailUi.empty()
    }

}

/*
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class FakeRepositoryModule {
    @Binds
    abstract fun bindsFoodRecipesRepository(
        impl: FakeRepository
    ): FoodRecipesRepository
}
*/