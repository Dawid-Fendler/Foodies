package pl.mapper.ingredientdetails

import pl.model.ingredientdetails.EstimatedCost
import pl.model.ingredientdetails.IngredientDetails
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.restmodel.ingredientsdetails.EstimatedCostResponse
import pl.restmodel.ingredientsdetails.IngredientDetailsResponse
import pl.restmodel.ingredientsdetails.IngredientSubstitutesResponse

fun IngredientDetailsResponse.toDomain() = IngredientDetails(
    name = name,
    possibleUnits = possibleUnits,
    amount = amount,
    estimateCost = estimateCost?.toDomain(),
    consistency = consistency,
    image = image,
    categoryPath = categoryPath
)

fun EstimatedCostResponse.toDomain() = EstimatedCost(
    value = value,
    unit = unit
)

fun IngredientSubstitutesResponse.toDomain() = IngredientSubstitutes(
    substitutes = substitutes
)