package pl.datasource.ingredientdetails

import io.reactivex.rxjava3.core.Observable
import pl.api.IngredientsDetailsApi
import pl.mapper.ingredientdetails.toDomain
import pl.model.ingredientdetails.IngredientDetails
import pl.model.ingredientdetails.IngredientSubstitutes
import javax.inject.Inject

class IngredientDetailsRemoteDataSource @Inject constructor(
    private val api: IngredientsDetailsApi
) : IngredientDetailsDataSource {
    override fun getIngredientDetails(id: Int): Observable<IngredientDetails> {
        return api.getIngredientDetails(id).map { it.toDomain() }
    }

    override fun getIngredientSubstitutes(name: String): Observable<IngredientSubstitutes> {
        return api.getIngredientSubstitutes(name).map { it.toDomain() }
    }
}