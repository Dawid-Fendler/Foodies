package pl.ingredientdetails

import pl.model.ingredientdetails.EstimatedCost
import pl.model.ingredientdetails.IngredientDetails
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.uimodel.ingredientdetails.EstimatedCostUiModel
import pl.uimodel.ingredientdetails.IngredientFullDetailsUiModel

fun mapToUiModel(
    ingredientDetails: IngredientDetails,
    ingredientsSubstitutes: IngredientSubstitutes
) = IngredientFullDetailsUiModel(
    name = ingredientDetails.name,
    possibleUnits = ingredientDetails.possibleUnits,
    amount = ingredientDetails.amount,
    estimateCost = ingredientDetails.estimateCost?.toUiModel(),
    consistency = ingredientDetails.consistency,
    image = ingredientDetails.image,
    categoryPath = ingredientDetails.categoryPath,
    substitutes = ingredientsSubstitutes.substitutes
)

fun EstimatedCost.toUiModel() = EstimatedCostUiModel(
    value = value,
    unit = unit
)