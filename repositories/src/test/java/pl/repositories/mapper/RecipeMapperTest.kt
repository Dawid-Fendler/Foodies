package pl.repositories.mapper

import junit.framework.TestCase.assertEquals
import org.junit.Test
import pl.mapper.toDomain
import pl.model.Recipe
import pl.model.RecipeResult
import pl.restmodel.RecipeResponse
import pl.restmodel.RecipeResultResponse

class RecipeMapperTest {

    @Test
    fun `map recipe response rest model to recipe domain model`() {
        val response = RecipeResponse(results = listOf(RecipeResultResponse(
            id = 1,
            image = "Image",
            title = "Title"
        )))
        val domain = response.toDomain()

        assertEquals(domain, Recipe(listOf(RecipeResult(
            recipeId = 1,
            image = "Image",
            title = "Title"
        ))))
    }
}