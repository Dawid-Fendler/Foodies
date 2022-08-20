package pl.repositories

import pl.datasource.RecipeDataSource
import pl.di.DataSourceModule
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    @DataSourceModule.RecipeRemoteDataSource
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {

    override fun getRecipes(recipeLimit: Int) =
        recipeDataSource
            .getRecipes(recipeLimit)
}