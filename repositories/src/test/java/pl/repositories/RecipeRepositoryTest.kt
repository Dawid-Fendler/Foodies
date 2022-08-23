package pl.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import pl.datasource.RecipeDataSource
import pl.repositories.model.recipe

class RecipeRepositoryTest {

    private val dataSource: RecipeDataSource = mock()
    private val repository: RecipeRepository = RecipeRepositoryImpl(dataSource)

    @Test
    fun `return recipes when get recipes is called`() {
        whenever(dataSource.getRecipes()).thenReturn(Observable.just(recipe))

        repository
            .getRecipes()
            .test()
            .assertValue(recipe)
    }
}