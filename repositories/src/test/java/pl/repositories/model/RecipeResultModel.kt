package pl.repositories.model

import pl.model.recipes.Recipe
import pl.model.recipes.RecipeResult
import pl.restmodel.recipes.RecipeResponse
import pl.restmodel.recipes.RecipeResultResponse

val recipeResultResponse = RecipeResultResponse(
    id = 1,
    image = "Image",
    title = "Title"
)

val recipeResult = RecipeResult(
    recipeId = 1,
    image = "Image",
    title = "Title"
)

val recipeResponse = RecipeResponse(listOf(recipeResultResponse))

val recipe = Recipe(listOf(recipeResult))