package pl.recipedetails

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import pl.architecture.SingleLiveEvent
import pl.architecture.base.BaseViewModel
import pl.usecase.recipedetails.GetRecipeDetailsUseCase
import pl.usecase.recipedetails.GetRecipeDetailsUseCase.Result.Failure
import pl.usecase.recipedetails.GetRecipeDetailsUseCase.Result.Success
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : BaseViewModel() {

    private val recipeDetailsViewState: MutableLiveData<RecipeDetailsViewState> = MutableLiveData()
    fun getRecipeDetailsViewState() = recipeDetailsViewState


    private val progressLoadingEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    fun getProgressLoadingEvent() = progressLoadingEvent

    fun init(recipeId: Int) {
        getRecipeDetails(recipeId)
    }

    private fun getRecipeDetails(recipeId: Int) {
        compositeDisposable += getRecipeDetailsUseCase
            .run(recipeId)
            .doOnSubscribe { progressLoadingEvent.postValue(true) }
            .doOnTerminate { progressLoadingEvent.postValue(false) }
            .subscribe { result ->
                when (result) {
                    is Success -> recipeDetailsViewState.postValue(
                        RecipeDetailsViewState.RecipeDetailsLoaded(
                            result = result.data.toUiModel()
                        )
                    )
                    is Failure -> {
                        recipeDetailsViewState.postValue(
                            RecipeDetailsViewState.RecipeDetailsLoadFailure(
                                throwable = result.throwable
                            )
                        )
                    }
                }
            }
    }
}