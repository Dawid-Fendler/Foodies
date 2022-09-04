package pl.ingredientdetails

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.zipWith
import pl.architecture.SingleLiveEvent
import pl.architecture.base.BaseViewModel
import pl.usecase.ingredientdetails.GetIngredientDetailsUseCase
import pl.usecase.ingredientdetails.GetIngredientSubstitutesUseCase
import javax.inject.Inject

@HiltViewModel
class IngredientDetailsViewModel @Inject constructor(
    private val getIngredientDetailsUseCase: GetIngredientDetailsUseCase,
    private val getIngredientSubstitutesUseCase: GetIngredientSubstitutesUseCase
) : BaseViewModel() {

    private val ingredientDetailsViewState: MutableLiveData<IngredientDetailsViewState> = MutableLiveData()
    fun getIngredientDetailsViewState() = ingredientDetailsViewState

    private val progressLoadingEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    fun getProgressLoadingEvent() = progressLoadingEvent

    fun getIngredientDetails(id: Int, name: String) {
        getIngredientDetailsUseCase.run(id)
            .zipWith(getIngredientSubstitutesUseCase.run(name))
            .doOnSubscribe { progressLoadingEvent.postValue(true) }
            .doOnTerminate { progressLoadingEvent.postValue(false) }
            .subscribeBy(
                onNext = {
                    ingredientDetailsViewState.postValue(
                        IngredientDetailsViewState.IngredientDetailsLoaded(
                            result = mapToUiModel(
                                ingredientDetails = it.first,
                                ingredientsSubstitutes = it.second
                            )
                        )
                    )
                },
                onError = { throwable ->
                    ingredientDetailsViewState.postValue(
                        IngredientDetailsViewState.IngredientDetailsLoadFailure(throwable)
                    )
                }
            )
    }
}
