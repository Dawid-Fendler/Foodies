package pl.usecase.ingredientdetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.repositories.IngredientDetailsRepository
import javax.inject.Inject

class GetIngredientSubstitutesUseCase @Inject constructor(
    private val repository: IngredientDetailsRepository
) {

    fun run(name: String): Observable<IngredientSubstitutes> {
        return repository
            .getIngredientSubstitutes(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    sealed class Result {
        data class Success(val data: IngredientSubstitutes) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}