package pl.recipedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.usecase.recipedetails.GetRecipeDetailsUseCase
import pl.usecase.recipedetails.GetRecipeDetailsUseCase.Result.Failure
import pl.usecase.recipedetails.GetRecipeDetailsUseCase.Result.Success


class RecipeDetailsViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetRecipeDetailsUseCase = mock()
    private lateinit var viewModelTest: RecipeDetailsViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModelTest = RecipeDetailsViewModel(useCase)
    }

    @Test
    fun `show error snackbar, when get recipe details returns Failure`() {
        val throwable = Throwable()
        val recipeDetailsLiveDataObserver =
            mock<Observer<pl.recipedetails.RecipeDetailsViewState>>()
        viewModelTest.getRecipeDetailsViewState().observeForever(recipeDetailsLiveDataObserver)
        whenever(useCase.run(1)).thenReturn(Observable.just(Failure(throwable)))

        viewModelTest.init(1)

        verify(recipeDetailsLiveDataObserver)
            .onChanged(RecipeDetailsViewState.RecipeDetailsLoadFailure(throwable))
    }

    @Test
    fun `load recipe details and map to uiModel , when get recipe details returns Success`() {
        val recipeDetailsLiveDataObserver =
            mock<Observer<pl.recipedetails.RecipeDetailsViewState>>()
        viewModelTest.getRecipeDetailsViewState().observeForever(recipeDetailsLiveDataObserver)
        whenever(useCase.run(1)).thenReturn(Observable.just(Success(recipeDetails)))

        viewModelTest.init(1)

        verify(recipeDetailsLiveDataObserver)
            .onChanged(RecipeDetailsViewState.RecipeDetailsLoaded(recipeDetails.toUiModel()))
    }

    @Test
    fun `fire progressLoadingEvent when recipe details are loading`() {
        val progressLoadingEventLiveDataObserver = mock<Observer<Boolean>>()
        viewModelTest.getProgressLoadingEvent().observeForever(progressLoadingEventLiveDataObserver)
        whenever(useCase.run(1)).thenReturn(Observable.just(Success(recipeDetails)))

        viewModelTest.init(1)

        verify(progressLoadingEventLiveDataObserver, times(2)).onChanged(any())
    }
}