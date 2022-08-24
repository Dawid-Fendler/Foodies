package pl.repositories.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.repositories.RecipeDetailsRepository
import pl.repositories.model.recipeDetails
import pl.usecase.recipedetails.GetRecipeDetailsUseCase
import pl.usecase.recipedetails.GetRecipeDetailsUseCase.Result.Failure
import pl.usecase.recipedetails.GetRecipeDetailsUseCase.Result.Success

class GetRecipeDetailsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: RecipeDetailsRepository = mock()
    private val useCase: GetRecipeDetailsUseCase = GetRecipeDetailsUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `return error when get recipe details returns error`() {
        val throwable = Throwable()
        whenever(repository.getRecipeDetails(1)).thenReturn(Observable.error(throwable))

        useCase
            .run(1)
            .test()
            .assertValue(Failure(throwable))
    }

    @Test
    fun `return recipe details when get recipe details returns recipes`() {
        whenever(repository.getRecipeDetails(1)).thenReturn(Observable.just(recipeDetails))

        useCase
            .run(1)
            .test()
            .assertValue(Success(recipeDetails))
    }
}