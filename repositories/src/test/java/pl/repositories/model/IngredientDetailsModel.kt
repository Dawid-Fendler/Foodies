package pl.repositories.model

import pl.model.ingredientdetails.EstimatedCost
import pl.model.ingredientdetails.IngredientDetails
import pl.restmodel.ingredientsdetails.EstimatedCostResponse
import pl.restmodel.ingredientsdetails.IngredientDetailsResponse

val ingredientDetailsResponse = IngredientDetailsResponse(
    name = "Name",
    possibleUnits = listOf("unit"),
    amount = 10.00,
    estimateCost = EstimatedCostResponse(value = 10.00, unit = "unit"),
    consistency = "consistency",
    image = "image",
    categoryPath = listOf("category")
)

val ingredientDetails = IngredientDetails(
    name = "Name",
    possibleUnits = listOf("unit"),
    amount = 10.00,
    estimateCost = EstimatedCost(value = 10.00, unit = "unit"),
    consistency = "consistency",
    image = "image",
    categoryPath = listOf("category")
)