package pl.repositories.model

import pl.model.recipedetails.ExtendedIngredient
import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
import pl.restmodel.recipedetails.ExtendedIngredientResponse
import pl.restmodel.recipedetails.RecipeDetailsResponse
import pl.restmodel.recipedetails.WinePairingResponse

val winePairingResponse = WinePairingResponse(
    pairedWines = listOf("Wine")
)

val extendedIngredientResponse = ExtendedIngredientResponse(
    id = 1,
    image = "Image",
    name = "Name",
    unit = "Unit",
    amount = 10.00
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
    winePairing = winePairingResponse,
    extendedIngredients = listOf(extendedIngredientResponse)
)

val extendedIngredient = ExtendedIngredient(
    id = 1,
    image = "Image",
    name = "Name",
    unit = "Unit",
    amount = 10.00
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
    winePairing = winePairing,
    ingredients = listOf(extendedIngredient)
)