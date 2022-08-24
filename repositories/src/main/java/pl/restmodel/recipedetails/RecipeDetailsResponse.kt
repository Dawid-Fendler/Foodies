package pl.restmodel.recipedetails

data class RecipeDetailsResponse(
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val dishTypes: List<String>,
    val summary: String,
    val winePairing: WinePairingResponse
)