package pl.repositories

import pl.datasource.ingredientdetails.IngredientDetailsDataSource
import pl.di.DataSourceModule
import javax.inject.Inject

class IngredientDetailsRepositoryImpl @Inject constructor(
    @DataSourceModule.IngredientDetailsRemoteDataSource
    private val ingredientDetailsDataSource: IngredientDetailsDataSource
) : IngredientDetailsRepository {
    override fun getIngredientDetails(id: Int) =
        ingredientDetailsDataSource
            .getIngredientDetails(id)

    override fun getIngredientSubstitutes(name: String) =
        ingredientDetailsDataSource
            .getIngredientSubstitutes(name)
}