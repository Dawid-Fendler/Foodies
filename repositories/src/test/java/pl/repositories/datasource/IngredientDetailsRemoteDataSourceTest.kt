package pl.repositories.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import pl.api.IngredientsDetailsApi
import pl.datasource.ingredientdetails.IngredientDetailsRemoteDataSource
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.repositories.model.ingredientDetails
import pl.repositories.model.ingredientDetailsResponse
import pl.restmodel.ingredientsdetails.IngredientSubstitutesResponse

class IngredientDetailsRemoteDataSourceTest {

    private val api: IngredientsDetailsApi = mock()
    private val dataSource = IngredientDetailsRemoteDataSource(api)

    @Test
    fun `get ingredient details and map to domain model`() {
        whenever(api.getIngredientDetails(1)).thenReturn(Observable.just(ingredientDetailsResponse))

        dataSource
            .getIngredientDetails(1)
            .test()
            .assertResult(ingredientDetails)
    }

    @Test
    fun `get ingredient substitutes and map to domain model`() {
        whenever(api.getIngredientSubstitutes("name")).thenReturn(
            Observable.just(
                IngredientSubstitutesResponse(listOf("substitute"))
            )
        )

        dataSource
            .getIngredientSubstitutes("name")
            .test()
            .assertResult(IngredientSubstitutes(listOf("substitute")))
    }
}