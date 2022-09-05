package pl.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import pl.datasource.ingredientdetails.IngredientDetailsDataSource
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.repositories.model.ingredientDetails

class IngredientDetailsRepositoryTest {

    private val dataSource: IngredientDetailsDataSource = mock()
    private val repository: IngredientDetailsRepository = IngredientDetailsRepositoryImpl(dataSource)

    @Test
    fun `return ingredient details when get ingredient details is called`() {
        whenever(dataSource.getIngredientDetails(1)).thenReturn(Observable.just(ingredientDetails))

        repository
            .getIngredientDetails(1)
            .test()
            .assertValue(ingredientDetails)
    }

    @Test
    fun `return ingredient substitutes when get ingredient substitutes is called`() {
        whenever(dataSource.getIngredientSubstitutes("name")).thenReturn(
            Observable.just(
                IngredientSubstitutes(
                    listOf("substitute")
                )
            )
        )

        repository
            .getIngredientSubstitutes("name")
            .test()
            .assertValue(IngredientSubstitutes(listOf("substitute")))
    }
}