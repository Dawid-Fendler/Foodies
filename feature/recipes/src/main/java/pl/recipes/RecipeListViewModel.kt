package pl.recipes

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import pl.architecture.SingleLiveEvent
import pl.architecture.base.BaseViewModel
import pl.usecase.recipes.GetRecipeUseCase
import pl.usecase.recipes.GetRecipeUseCase.Result.*
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase
) : BaseViewModel() {

    private val recipeListViewState: MutableLiveData<RecipeListViewState> = MutableLiveData()
    fun getRecipesListViewState() = recipeListViewState

    private val progressLoadingEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    fun getProgressLoadingEvent() = progressLoadingEvent

    fun init() {
        getRecipes()
    }

    private fun getRecipes() {
        compositeDisposable += getRecipeUseCase
            .run()
            .doOnSubscribe { progressLoadingEvent.postValue(true) }
            .doOnTerminate { progressLoadingEvent.postValue(false) }
            .subscribe { result ->
                when (result) {
                    is Success -> recipeListViewState.postValue(
                        RecipeListViewState.RecipesListLoaded(
                            result = result.data.toUiModel()
                        )
                    )
                    is Failure -> recipeListViewState.postValue(
                        RecipeListViewState.RecipeListLoadFailure(
                            throwable = result.throwable
                        )
                    )
                    is EmptyList -> recipeListViewState.postValue(
                        RecipeListViewState.RecipesEmpty(
                            errorMessage = result.errorMessage
                        )
                    )
                }
            }
    }
}