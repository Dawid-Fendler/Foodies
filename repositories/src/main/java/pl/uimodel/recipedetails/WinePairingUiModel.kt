package pl.uimodel.recipedetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WinePairingUiModel(
    val wines: List<String>
) : Parcelable