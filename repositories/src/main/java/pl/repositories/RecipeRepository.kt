package pl.repositories

import io.reactivex.rxjava3.core.Observable
import pl.model.Recipe

interface RecipeRepository {

    fun getRecipes(): Observable<Recipe>
}