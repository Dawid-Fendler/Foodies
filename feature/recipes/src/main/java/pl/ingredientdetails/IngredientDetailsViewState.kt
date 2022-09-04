package pl.ingredientdetails

sealed class IngredientDetailsViewState {
    data class IngredientDetailsLoaded(
        val result: IngredientFullDetailsUiModel
    ) : IngredientDetailsViewState()

    data class IngredientDetailsLoadFailure(val throwable: Throwable) : IngredientDetailsViewState()
}