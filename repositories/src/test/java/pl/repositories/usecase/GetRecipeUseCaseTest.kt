package pl.repositories.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.repositories.RecipeRepository
import pl.repositories.model.recipe
import pl.usecase.GetRecipeUseCase.Result.Failure
import pl.usecase.GetRecipeUseCase.Result.Success
import pl.usecase.GetRecipeUseCase


class GetRecipeUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: RecipeRepository = mock()
    private val useCase: GetRecipeUseCase = GetRecipeUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{ Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler{ Schedulers.trampoline() }
    }

    @Test
    fun `return error result when get recipes returns error`() {
        val throwable = Throwable()
        whenever(repository.getRecipes(1)).thenReturn(Observable.error(throwable))

        useCase
            .invoke(1)
            .test()
            .assertValue(Failure(throwable))
    }

    @Test
    fun `return success result when get recipes returns recipes`() {
        whenever(repository.getRecipes(1)).thenReturn(Observable.just(recipe))

        useCase
            .invoke(1)
            .test()
            .assertValue(Success(recipe))
    }
}