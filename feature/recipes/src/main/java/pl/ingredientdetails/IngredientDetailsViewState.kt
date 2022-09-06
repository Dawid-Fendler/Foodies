package pl.ingredientdetails

import pl.uimodel.ingredientdetails.IngredientFullDetailsUiModel

sealed class IngredientDetailsViewState {
    data class IngredientDetailsLoaded(
        val result: IngredientFullDetailsUiModel
    ) : IngredientDetailsViewState()

    data class IngredientDetailsLoadFailure(val throwable: Throwable) : IngredientDetailsViewState()
}