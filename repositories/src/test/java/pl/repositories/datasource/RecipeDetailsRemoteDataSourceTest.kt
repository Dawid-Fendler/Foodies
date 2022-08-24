package pl.repositories.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import pl.api.RecipeDetailsApi
import pl.datasource.recipedetails.RecipeDetailsRemoteDataSource
import pl.repositories.model.recipeDetails
import pl.repositories.model.recipeDetailsResponse

class RecipeDetailsRemoteDataSourceTest {

    private val api: RecipeDetailsApi = mock()
    private val dataSource = RecipeDetailsRemoteDataSource(api)

    @Test
    fun `get recipe details and map to domain model`() {
        whenever(api.getRecipeDetails(1)).thenReturn(Observable.just(recipeDetailsResponse))

        dataSource.getRecipeDetails(1).test().assertResult(recipeDetails)
    }
}