package pl.repositories.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.mapper.recipedetails.toDomain
import pl.repositories.model.recipeDetails
import pl.repositories.model.recipeDetailsResponse
import pl.repositories.model.winePairing
import pl.repositories.model.winePairingResponse

class RecipeDetailsMapperTest {

    @Test
    fun `map recipe details response rest model to recipe details domain model`() {
        val response = recipeDetailsResponse
        val domain = response.toDomain()

        assertEquals(domain, recipeDetails)
    }

    @Test
    fun `map wine pairing response rest model to wine pairing domain model`() {
        val response = winePairingResponse
        val domain = response.toDomain()

        assertEquals(domain, winePairing)
    }
}