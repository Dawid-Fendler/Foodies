package pl.repositories

import pl.datasource.recipedetails.RecipeDetailsDataSource
import pl.di.DataSourceModule
import javax.inject.Inject

class RecipeDetailsRepositoryImpl @Inject constructor(
    @DataSourceModule.RecipeDetailsRemoteDataSource
    private val recipeDetailsDataSource: RecipeDetailsDataSource
) : RecipeDetailsRepository {
    override fun getRecipeDetails(id: Int) =
        recipeDetailsDataSource
            .getRecipeDetails(id)
}