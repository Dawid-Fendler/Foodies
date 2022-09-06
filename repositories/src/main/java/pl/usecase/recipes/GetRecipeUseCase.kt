package pl.usecase.recipes

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.model.recipes.Recipe
import pl.repositories.RecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository,
) {

    fun run(): Observable<Result> {
        return repository
            .getRecipes()
            .map { recipe ->
                if (recipe.recipeList.isEmpty()) {
                    Result.EmptyList("Couldn't find any recipes")
                } else {
                    Result.Success(recipe) as Result
                }
            }
            .onErrorReturn { Result.Failure(it) as Result }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    sealed class Result {
        data class Success(val data: Recipe) : Result()
        data class EmptyList(val errorMessage: String) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}