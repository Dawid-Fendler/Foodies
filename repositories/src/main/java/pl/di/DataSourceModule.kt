package pl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.api.RecipeDetailsApi
import pl.api.RecipesApi
import pl.datasource.recipedetails.RecipeDetailsDataSource
import pl.datasource.recipedetails.RecipeDetailsRemoteDataSource
import pl.datasource.recipes.RecipeDataSource
import pl.datasource.recipes.RecipeRemoteDataSource
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RecipeRemoteDataSource

    @Singleton
    @RecipeRemoteDataSource
    @Provides
    fun provideRecipeDataSource(recipesApi: RecipesApi): RecipeDataSource =
        RecipeRemoteDataSource(recipesApi)

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RecipeDetailsRemoteDataSource

    @Singleton
    @RecipeDetailsRemoteDataSource
    @Provides
    fun provideRecipeDetailsDataSource(recipeDetailsApi: RecipeDetailsApi): RecipeDetailsDataSource =
        RecipeDetailsRemoteDataSource(recipeDetailsApi)
}