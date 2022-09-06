package pl.usecase.ingredientdetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.model.ingredientdetails.IngredientDetails
import pl.repositories.IngredientDetailsRepository
import javax.inject.Inject

class GetIngredientDetailsUseCase @Inject constructor(
    private val repository: IngredientDetailsRepository
) {
    fun run(id: Int): Observable<IngredientDetails> {
        return repository
            .getIngredientDetails(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}