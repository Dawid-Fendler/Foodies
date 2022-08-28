package pl.recipedetails

import pl.uimodel.recipedetails.RecipeDetailsUiModel

sealed class RecipeDetailsViewState {
    data class RecipeDetailsLoaded(val result: RecipeDetailsUiModel) : RecipeDetailsViewState()
    data class RecipeDetailsLoadFailure(val throwable: Throwable) : RecipeDetailsViewState()
}