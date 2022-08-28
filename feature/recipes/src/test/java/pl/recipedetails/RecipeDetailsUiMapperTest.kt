package pl.recipedetails

import org.junit.Assert.assertEquals
import org.junit.Test

class RecipeDetailsUiMapperTest {

    @Test
    fun `map recipe details domain model to recipe details ui model`() {
        val recipeDomain = recipeDetails

        val recipeUi = recipeDomain.toUiModel()

        assertEquals(recipeUi, recipeDetailsUiModel)
    }
}