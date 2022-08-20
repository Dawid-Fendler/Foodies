package pl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.api.RecipeApi
import pl.datasource.RecipeDataSource
import pl.datasource.RecipeRemoteDataSource
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
    fun provideRecipeDataSource(recipesApi: RecipeApi): RecipeDataSource =
        RecipeRemoteDataSource(recipesApi)
}