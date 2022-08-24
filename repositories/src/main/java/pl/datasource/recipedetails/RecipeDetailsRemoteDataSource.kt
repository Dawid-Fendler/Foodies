package pl.datasource.recipedetails

import io.reactivex.rxjava3.core.Observable
import pl.api.RecipeDetailsApi
import pl.mapper.recipedetails.toDomain
import pl.model.recipedetails.RecipeDetails
import javax.inject.Inject

class RecipeDetailsRemoteDataSource @Inject constructor(
    private val api: RecipeDetailsApi
) : RecipeDetailsDataSource {
    override fun getRecipeDetails(id: Int): Observable<RecipeDetails> {
        return api.getRecipeDetails(id).map { it.toDomain() }
    }
}