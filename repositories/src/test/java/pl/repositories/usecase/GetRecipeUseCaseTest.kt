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
import pl.model.recipes.Recipe
import pl.repositories.RecipeRepository
import pl.repositories.model.recipe
import pl.usecase.recipes.GetRecipeUseCase
import pl.usecase.recipes.GetRecipeUseCase.Result.*


class GetRecipeUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: RecipeRepository = mock()
    private val useCase: GetRecipeUseCase = GetRecipeUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `return error when get recipes returns failure`() {
        val throwable = Throwable()
        whenever(repository.getRecipes()).thenReturn(Observable.error(throwable))

        useCase
            .run()
            .test()
            .assertValue(Failure(throwable))
    }

    @Test
    fun `return error message when get recipes returns empty list`() {
        whenever(repository.getRecipes()).thenReturn(Observable.just(Recipe(listOf())))

        useCase
            .run()
            .test()
            .assertValue(EmptyList("Couldn't find any recipes"))
    }

    @Test
    fun `return recipes when get recipes returns recipes`() {
        whenever(repository.getRecipes()).thenReturn(Observable.just(recipe))

        useCase
            .run()
            .test()
            .assertValue(Success(recipe))
    }
}