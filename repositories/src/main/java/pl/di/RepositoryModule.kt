package pl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.repositories.RecipeDetailsRepository
import pl.repositories.RecipeDetailsRepositoryImpl
import pl.repositories.RecipeRepository
import pl.repositories.RecipeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    @Singleton
    fun bindsRecipeDetailsRepository(impl: RecipeDetailsRepositoryImpl): RecipeDetailsRepository
}