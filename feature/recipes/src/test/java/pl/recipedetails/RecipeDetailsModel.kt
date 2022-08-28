package pl.recipedetails

import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
import pl.model.recipes.Recipe
import pl.model.recipes.RecipeResult
import pl.uimodel.recipedetails.RecipeDetailsUiModel
import pl.uimodel.recipedetails.WinePairingUiModel
import pl.uimodel.recipes.RecipeResultUiModel
import pl.uimodel.recipes.RecipeUiModel

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
    winePairing = WinePairing(listOf("Wine"))
)

val recipeDetailsUiModel = RecipeDetailsUiModel(
    title = "Title",
    image = "Image",
    minutes = 1,
    likes = 1,
    dishTypes = listOf("Types"),
    summary = "Summary",
    winePairing = WinePairingUiModel(listOf("Wine")),
    dietTypes = listOf()
)