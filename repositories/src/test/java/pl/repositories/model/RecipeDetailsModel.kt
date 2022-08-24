package pl.repositories.model

import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
import pl.restmodel.recipedetails.RecipeDetailsResponse
import pl.restmodel.recipedetails.WinePairingResponse

val winePairingResponse = WinePairingResponse(
    pairedWines = listOf("Wine")
)

val recipeDetailsResponse = RecipeDetailsResponse(
    title = "Title",
    image = "Image",
    readyInMinutes = 10,
    aggregateLikes = 10,
    cheap = false,
    dairyFree = false,
    glutenFree = false,
    vegan = false,
    vegetarian = false,
    dishTypes = listOf("Test"),
    summary = "Summary",
    winePairing = winePairingResponse
)

val winePairing = WinePairing(
    wines = listOf("Wine")
)

val recipeDetails = RecipeDetails(
    title = "Title",
    image = "Image",
    minutes = 10,
    likes = 10,
    cheap = false,
    dairyFree = false,
    glutenFree = false,
    vegan = false,
    vegetarian = false,
    dishTypes = listOf("Test"),
    summary = "Summary",
    winePairing = winePairing
)