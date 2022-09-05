package pl.repositories.mapper

import org.junit.Assert
import org.junit.Test
import pl.mapper.ingredientdetails.toDomain
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.repositories.model.ingredientDetails
import pl.repositories.model.ingredientDetailsResponse
import pl.restmodel.ingredientsdetails.IngredientSubstitutesResponse

class IngredientDetailsMapperTest {

    @Test
    fun `map ingredient details response rest model to ingredient details domain model`() {
        val response = ingredientDetailsResponse
        val domain = response.toDomain()

        Assert.assertEquals(domain, ingredientDetails)
    }

    @Test
    fun `map ingredient substitutes response rest model to ingredient substitutes domain model`() {
        val response = IngredientSubstitutesResponse(listOf("substitute"))
        val domain = response.toDomain()

        Assert.assertEquals(domain, IngredientSubstitutes(listOf("substitute")))
    }
}