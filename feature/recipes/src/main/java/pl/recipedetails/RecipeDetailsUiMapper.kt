package pl.recipedetails

import pl.model.recipedetails.RecipeDetails
import pl.model.recipedetails.WinePairing
import pl.uimodel.recipedetails.RecipeDetailsUiModel
import pl.uimodel.recipedetails.WinePairingUiModel

fun RecipeDetails.toUiModel() = RecipeDetailsUiModel(
    title = title,
    image = image,
    minutes = minutes,
    likes = likes,
    summary = summary,
    winePairing = winePairing.toUiModel(),
    dishTypes = dishTypes,
    dietTypes = createDietTypesList(vegan, vegetarian, glutenFree, cheap, dairyFree)
)

fun WinePairing.toUiModel() = WinePairingUiModel(
    wines = wines
)

fun createDietTypesList(
    vegan: Boolean,
    vegetarian: Boolean,
    glutenFree: Boolean,
    cheap: Boolean,
    dairyFree: Boolean
): List<String> {
    val dietTypes: List<Pair<String, Boolean>> = listOf(
        Pair("Vegan", vegan),
        Pair("Vegetarian", vegetarian),
        Pair("Gluten Free", glutenFree),
        Pair("Cheap", cheap),
        Pair("Dairy Free", dairyFree)
    )
    return mutableListOf<String>().apply {
        dietTypes.indices
            .asSequence()
            .filter { dietTypes[it].second }
            .forEach { add(dietTypes[it].first) }
    }
}