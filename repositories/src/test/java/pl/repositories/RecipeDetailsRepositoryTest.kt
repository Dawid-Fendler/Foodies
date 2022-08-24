package pl.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import pl.datasource.recipedetails.RecipeDetailsDataSource
import pl.repositories.model.recipeDetails

class RecipeDetailsRepositoryTest {

    private val dataSource: RecipeDetailsDataSource = mock()
    private val repository: RecipeDetailsRepository = RecipeDetailsRepositoryImpl(dataSource)

    @Test
    fun `return recipe details when get recipe details is called`() {
        whenever(dataSource.getRecipeDetails(1)).thenReturn(Observable.just(recipeDetails))

        repository
            .getRecipeDetails(1)
            .test()
            .assertValue(recipeDetails)
    }
}