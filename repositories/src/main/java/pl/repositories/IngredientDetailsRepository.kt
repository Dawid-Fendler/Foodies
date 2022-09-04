package pl.repositories

import io.reactivex.rxjava3.core.Observable
import pl.model.ingredientdetails.IngredientDetails
import pl.model.ingredientdetails.IngredientSubstitutes

interface IngredientDetailsRepository {
    fun getIngredientDetails(id: Int): Observable<IngredientDetails>
    fun getIngredientSubstitutes(name: String): Observable<IngredientSubstitutes>
}