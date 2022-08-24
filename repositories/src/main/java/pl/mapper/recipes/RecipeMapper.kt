package pl.mapper

import pl.model.recipes.Recipe
import pl.model.recipes.RecipeResult
import pl.restmodel.recipes.RecipeResponse
import pl.restmodel.recipes.RecipeResultResponse

fun RecipeResponse.toDomain() = Recipe(
    recipeList = results.map { it.toDomain() }
)

fun RecipeResultResponse.toDomain() = RecipeResult(
    recipeId = id,
    image = image,
    title = title
)