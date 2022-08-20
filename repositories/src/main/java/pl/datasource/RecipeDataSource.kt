package pl.datasource

import io.reactivex.rxjava3.core.Observable
import pl.model.Recipe
import pl.model.RecipeResult

interface RecipeDataSource {

    fun getRecipes(recipeLimit: Int): Observable<Recipe>
}