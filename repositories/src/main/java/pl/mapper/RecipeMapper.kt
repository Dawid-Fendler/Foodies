package pl.mapper

import pl.model.Recipe
import pl.model.RecipeResult
import pl.restmodel.RecipeResponse
import pl.restmodel.RecipeResultResponse

fun RecipeResponse.toDomain() = Recipe(
    recipeList = results.map { it.toDomain() }
)

fun RecipeResultResponse.toDomain() = RecipeResult(
    recipeId = id,
    image = image,
    title = title
)