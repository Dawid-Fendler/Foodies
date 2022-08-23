package pl.datasource

import io.reactivex.rxjava3.core.Observable
import pl.api.RecipeApi
import pl.mapper.toDomain
import pl.model.Recipe
import javax.inject.Inject

class RecipeRemoteDataSource @Inject constructor(
    private val recipeApi: RecipeApi
) : RecipeDataSource {

    override fun getRecipes(): Observable<Recipe> {
        return recipeApi.getRecipes().map { it.toDomain() }
    }
}