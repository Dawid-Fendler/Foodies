package pl.datasource.recipes

import io.reactivex.rxjava3.core.Observable
import pl.api.RecipesApi
import pl.mapper.recipes.toDomain
import pl.model.recipes.Recipe
import javax.inject.Inject

class RecipeRemoteDataSource @Inject constructor(
    private val recipeApi: RecipesApi
) : RecipeDataSource {
    override fun getRecipes(): Observable<Recipe> {
        return recipeApi.getRecipes().map { it.toDomain() }
    }
}