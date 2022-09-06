package pl.ingredientdetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientDetailsNavigationModel(
    val id: Int,
    val name: String
) : Parcelable