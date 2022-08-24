package pl.repositories

import io.reactivex.rxjava3.core.Observable
import pl.model.recipedetails.RecipeDetails

interface RecipeDetailsRepository {
    fun getRecipeDetails(id: Int): Observable<RecipeDetails>
}