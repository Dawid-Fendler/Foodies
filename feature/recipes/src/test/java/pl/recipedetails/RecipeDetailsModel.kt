package pl.recipedetails

import pl.model.recipedetails.ExtendedIngredient
import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
import pl.uimodel.recipedetails.ExtendedIngredientUiModel
import pl.uimodel.recipedetails.RecipeDetailsUiModel
import pl.uimodel.recipedetails.WinePairingUiModel

val extendedIngredient = ExtendedIngredient(
    id = 1,
    image = "Image",
    name = "Name",
    unit = "Unit",
    amount = 10.00
)

val recipeDetails = RecipeDetails(
    title = "Title",
    image = "Image",
    minutes = 1,
    likes = 1,
    cheap = false,
    dairyFree = false,
    glutenFree = false,
    vegan = false,
    vegetarian = false,
    dishTypes = listOf("Types"),
    summary = "Summary",
    winePairing = WinePairing(listOf("Wine")),
    ingredients = listOf(extendedIngredient)
)

val extendedIngredientUiModel = ExtendedIngredientUiModel(
    id = 1,
    image = "Image",
    name = "Name",
    unit = "Unit",
    amount = 10.00
)

val recipeDetailsUiModel = RecipeDetailsUiModel(
    title = "Title",
    image = "Image",
    minutes = 1,
    likes = 1,
    dishTypes = listOf("Types"),
    summary = "Summary",
    winePairing = WinePairingUiModel(listOf("Wine")),
    dietTypes = listOf(),
    ingredients = listOf(extendedIngredientUiModel)
)