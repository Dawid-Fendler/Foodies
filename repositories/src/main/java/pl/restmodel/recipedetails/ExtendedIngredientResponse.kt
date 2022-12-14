package pl.restmodel.recipedetails

data class ExtendedIngredientResponse(
    val id: Int,
    val image: String,
    val name: String,
    val unit: String,
    val amount: Double,
)