package pl.mapper.recipedetails

import pl.model.recipedetails.ExtendedIngredient
import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
import pl.restmodel.recipedetails.ExtendedIngredientResponse
import pl.restmodel.recipedetails.RecipeDetailsResponse
import pl.restmodel.recipedetails.WinePairingResponse

fun RecipeDetailsResponse.toDomain() = RecipeDetails(
    title = title,
    image = image,
    minutes = readyInMinutes,
    likes = aggregateLikes,
    dairyFree = dairyFree,
    cheap = cheap,
    glutenFree = glutenFree,
    vegan = vegan,
    vegetarian = vegetarian,
    dishTypes = dishTypes,
    summary = summary,
    winePairing = winePairing.toDomain(),
    ingredients = extendedIngredients.map { it.toDomain() }
)

fun WinePairingResponse.toDomain() = WinePairing(
    wines = pairedWines
)

fun ExtendedIngredientResponse.toDomain() = ExtendedIngredient(
    id = id,
    image = image,
    name = name,
    unit = unit,
    amount = amount
)