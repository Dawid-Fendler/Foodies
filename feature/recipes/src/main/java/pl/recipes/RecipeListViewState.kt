package pl.recipes

import pl.uimodel.recipes.RecipeUiModel

sealed class RecipeListViewState {
    data class RecipesListLoaded(val result: RecipeUiModel) : RecipeListViewState()
    data class RecipesEmpty(val errorMessage: String) : RecipeListViewState()
    data class RecipeListLoadFailure(val throwable: Throwable) : RecipeListViewState()
}