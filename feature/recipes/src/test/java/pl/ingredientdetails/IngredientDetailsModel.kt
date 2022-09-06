package pl.ingredientdetails

import pl.model.ingredientdetails.EstimatedCost
import pl.model.ingredientdetails.IngredientDetails
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.uimodel.ingredientdetails.EstimatedCostUiModel
import pl.uimodel.ingredientdetails.IngredientFullDetailsUiModel

val ingredientDetails = IngredientDetails(
    name = "Name",
    possibleUnits = listOf("unit"),
    amount = 10.00,
    estimateCost = EstimatedCost(value = 10.00, unit = "unit"),
    consistency = "consistency",
    image = "image",
    categoryPath = listOf("category")
)

val ingredientSubstitutes = IngredientSubstitutes(
    listOf("substitute")
)

val ingredientFullDetailsUi = IngredientFullDetailsUiModel(
    name = "Name",
    possibleUnits = listOf("unit"),
    amount = 10.00,
    estimateCost = EstimatedCostUiModel(value = 10.00, unit = "unit"),
    consistency = "consistency",
    image = "image",
    categoryPath = listOf("category"),
    substitutes = listOf("substitute")
)