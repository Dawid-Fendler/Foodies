package pl.recipes

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
import pl.usecase.recipes.GetRecipeUseCase
import pl.usecase.recipes.GetRecipeUseCase.Result.Success
import pl.usecase.recipes.GetRecipeUseCase.Result.EmptyList
import pl.usecase.recipes.GetRecipeUseCase.Result.Failure


class RecipeListViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetRecipeUseCase = mock()
    private lateinit var viewModelTest: RecipeListViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{ Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler{ Schedulers.trampoline() }

        viewModelTest = RecipeListViewModel(useCase)
    }

    @Test
    fun `show empty state screen, when get recipes returns EmptyList`(){
        val recipesLiveDataObserver = mock<Observer<RecipeListViewState>>()
        viewModelTest.getRecipesListViewState().observeForever(recipesLiveDataObserver)
        whenever(useCase.run()).thenReturn(Observable.just(EmptyList("Message")))

        viewModelTest.init()

        verify(recipesLiveDataObserver).onChanged(RecipeListViewState.RecipesEmpty("Message"))
    }

    @Test
    fun `show error snackbar, when get recipes returns Failure`(){
        val throwable = Throwable()
        val recipesLiveDataObserver = mock<Observer<RecipeListViewState>>()
        viewModelTest.getRecipesListViewState().observeForever(recipesLiveDataObserver)
        whenever(useCase.run()).thenReturn(Observable.just(Failure(throwable)))

        viewModelTest.init()

        verify(recipesLiveDataObserver).onChanged(RecipeListViewState.RecipeListLoadFailure(throwable))
    }

    @Test
    fun `load recipes and map to uiModel , when get recipes returns Success`(){
        val recipesLiveDataObserver = mock<Observer<RecipeListViewState>>()
        viewModelTest.getRecipesListViewState().observeForever(recipesLiveDataObserver)
        whenever(useCase.run()).thenReturn(Observable.just(Success(recipe)))

        viewModelTest.init()

        verify(recipesLiveDataObserver).onChanged(RecipeListViewState.RecipesListLoaded(recipe.toUiModel()))
    }

    @Test
    fun `fire progressLoadingEvent when recipes are loading`() {
        val progressLoadingEventLiveDataObserver = mock<Observer<Boolean>>()
        viewModelTest.getProgressLoadingEvent().observeForever(progressLoadingEventLiveDataObserver)
        whenever(useCase.run()).thenReturn(Observable.just(Success(recipe)))

        viewModelTest.init()

        verify(progressLoadingEventLiveDataObserver, times(2)).onChanged(any())
    }
}