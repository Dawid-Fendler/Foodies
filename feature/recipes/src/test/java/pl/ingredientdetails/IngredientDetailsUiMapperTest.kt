package pl.ingredientdetails

import org.junit.Assert
import org.junit.Test

class IngredientDetailsUiMapperTest {

    @Test
    fun `map ingredient details domain model and ingredient substitutes to ingredient details ui model`() {
        val ingredientDetailsDomain = ingredientDetails
        val ingredientSubstitutesDomain = ingredientSubstitutes

        val ingredientDetailsUi = mapToUiModel(
            ingredientDetailsDomain,
            ingredientSubstitutesDomain
        )

        Assert.assertEquals(ingredientDetailsUi, ingredientFullDetailsUi)
    }
}