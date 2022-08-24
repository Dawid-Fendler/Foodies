package pl.recipes

import pl.model.recipes.Recipe
import pl.model.recipes.RecipeResult
import pl.uimodel.recipes.RecipeResultUiModel
import pl.uimodel.recipes.RecipeUiModel

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