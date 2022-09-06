package pl.repositories

import io.reactivex.rxjava3.core.Observable
import pl.model.recipes.Recipe

interface RecipeRepository {
    fun getRecipes(): Observable<Recipe>
}