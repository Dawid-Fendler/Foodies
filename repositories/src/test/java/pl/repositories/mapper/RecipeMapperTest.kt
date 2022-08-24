package pl.repositories.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.mapper.toDomain
import pl.model.recipes.Recipe
import pl.repositories.model.recipeResult
import pl.repositories.model.recipeResultResponse
import pl.restmodel.recipes.RecipeResponse

class RecipeMapperTest {

    @Test
    fun `map recipe response rest model to recipe domain model`() {
        val response = RecipeResponse(results = listOf(recipeResultResponse))
        val domain = response.toDomain()

        assertEquals(domain, Recipe(listOf(recipeResult)))
    }
}