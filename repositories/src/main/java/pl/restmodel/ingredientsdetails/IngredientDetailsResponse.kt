package pl.restmodel.ingredientsdetails

data class IngredientDetailsResponse(
    val name: String?,
    val possibleUnits: List<String>?,
    val amount: Double?,
    val estimateCost: EstimatedCostResponse?,
    val consistency: String?,
    val image: String?,
    val categoryPath: List<String>?
)