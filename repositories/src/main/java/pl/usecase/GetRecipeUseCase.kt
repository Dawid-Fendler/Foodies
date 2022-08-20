package pl.usecase

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.model.Recipe
import pl.repositories.RecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository,
) {

    operator fun invoke(recipeLimit: Int): Observable<Result> {
        return repository
            .getRecipes(recipeLimit)
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) as Result }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    sealed class Result {

        data class Success(val data: Recipe) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}