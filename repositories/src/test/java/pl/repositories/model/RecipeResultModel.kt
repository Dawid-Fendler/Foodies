package pl.repositories.model

import pl.model.Recipe
import pl.model.RecipeResult
import pl.restmodel.RecipeResponse
import pl.restmodel.RecipeResultResponse

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