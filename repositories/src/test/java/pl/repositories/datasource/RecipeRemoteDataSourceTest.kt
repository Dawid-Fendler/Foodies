package pl.repositories.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import pl.api.RecipeApi
import pl.datasource.RecipeRemoteDataSource
import pl.repositories.model.recipe
import pl.repositories.model.recipeResponse

class RecipeRemoteDataSourceTest {

    private val api: RecipeApi = mock()
    private val dataSource = RecipeRemoteDataSource(api)

    @Test
    fun `get recipe and map to domain model`() {
        whenever(api.getRecipes(1)).thenReturn(Observable.just(recipeResponse))

        dataSource.getRecipes(1).test().assertResult(recipe)
    }
}