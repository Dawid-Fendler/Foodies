package pl.uimodel.recipedetails

data class RecipeDetailsUiModel(
    val title: String,
    val image: String,
    val minutes: Int,
    val likes: Int,
    val summary: String,
    val winePairing: WinePairingUiModel,
    val dishTypes: List<String>,
    val dietTypes: List<String>
)