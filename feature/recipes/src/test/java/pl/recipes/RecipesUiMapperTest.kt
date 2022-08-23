package pl.recipes

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.uimodel.RecipeUiModel

class RecipesUiMapperTest {

    @Test
    fun `map recipes domain model to recipe ui model`() {
        val recipeDomain = recipe

        val recipeUi = recipeDomain.toUiModel()

        assertEquals(recipeUi, RecipeUiModel(listOf(recipeUiResult)))
    }
}