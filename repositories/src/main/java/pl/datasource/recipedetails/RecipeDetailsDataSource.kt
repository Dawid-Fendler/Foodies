package pl.datasource.recipedetails

import io.reactivex.rxjava3.core.Observable
import pl.model.recipedetails.RecipeDetails

interface RecipeDetailsDataSource {
    fun getRecipeDetails(id: Int): Observable<RecipeDetails>
}