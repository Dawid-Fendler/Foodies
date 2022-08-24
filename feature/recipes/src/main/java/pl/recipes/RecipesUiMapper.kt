package pl.recipes

import pl.model.recipes.Recipe
import pl.model.recipes.RecipeResult
import pl.uimodel.recipes.RecipeResultUiModel
import pl.uimodel.recipes.RecipeUiModel

fun Recipe.toUiModel() = RecipeUiModel(
    recipeList = recipeList.map { it.toUiModel() }
)

fun RecipeResult.toUiModel() = RecipeResultUiModel(
    recipeId = recipeId,
    image = image,
    title = title
)