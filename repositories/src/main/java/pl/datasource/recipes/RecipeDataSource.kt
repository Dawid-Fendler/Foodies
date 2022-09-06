package pl.datasource.recipes

import io.reactivex.rxjava3.core.Observable
import pl.model.recipes.Recipe

interface RecipeDataSource {
    fun getRecipes(): Observable<Recipe>
}