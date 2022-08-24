package pl.mapper.recipedetails

import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
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
    winePairing = winePairing.toDomain()
)

fun WinePairingResponse.toDomain() = WinePairing(
    wines = pairedWines
)