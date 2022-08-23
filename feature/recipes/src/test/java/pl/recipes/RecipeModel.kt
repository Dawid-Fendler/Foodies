package pl.recipes

import pl.model.Recipe
import pl.model.RecipeResult
import pl.uimodel.RecipeResultUiModel
import pl.uimodel.RecipeUiModel

val recipeResult = RecipeResult(
    recipeId = 1,
    image = "Image",
    title = "Title"
)

val recipe = Recipe(listOf(recipeResult))

val recipeUiResult = RecipeResultUiModel(
    recipeId = 1,
    image = "Image",
    title = "Title"
)

val recipeUi = RecipeUiModel(listOf(recipeUiResult))