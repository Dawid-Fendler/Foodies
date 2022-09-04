package pl.datasource.ingredientdetails

import io.reactivex.rxjava3.core.Observable
import pl.model.ingredientdetails.IngredientDetails
import pl.model.ingredientdetails.IngredientSubstitutes

interface IngredientDetailsDataSource {
    fun getIngredientDetails(id: Int): Observable<IngredientDetails>
    fun getIngredientSubstitutes(name: String): Observable<IngredientSubstitutes>
}