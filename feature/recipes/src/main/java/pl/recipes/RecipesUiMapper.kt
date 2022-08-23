package pl.recipes

import pl.model.Recipe
import pl.model.RecipeResult
import pl.uimodel.RecipeResultUiModel
import pl.uimodel.RecipeUiModel

fun Recipe.toUiModel() = RecipeUiModel(
    recipeList = recipeList.map { it.toUiModel() }
)

fun RecipeResult.toUiModel() = RecipeResultUiModel(
    recipeId = recipeId,
    image = image,
    title = title
)