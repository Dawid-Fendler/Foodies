package pl.usecase.recipedetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.model.recipedetails.RecipeDetails
import pl.repositories.RecipeDetailsRepository
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val repository: RecipeDetailsRepository
) {

    fun run(id: Int): Observable<Result> {
        return repository
            .getRecipeDetails(id)
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) as Result }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    sealed class Result {
        data class Success(val data: RecipeDetails) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}