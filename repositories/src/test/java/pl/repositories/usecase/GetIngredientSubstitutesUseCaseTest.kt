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
import pl.model.ingredientdetails.IngredientSubstitutes
import pl.repositories.IngredientDetailsRepository
import pl.usecase.ingredientdetails.GetIngredientSubstitutesUseCase

class GetIngredientSubstitutesUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: IngredientDetailsRepository = mock()
    private val useCase: GetIngredientSubstitutesUseCase =
        GetIngredientSubstitutesUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `return ingredient substitutes when get ingredient substitutes returns ingredient substitutes`() {
        whenever(repository.getIngredientSubstitutes("name")).thenReturn(
            Observable.just(
                IngredientSubstitutes(
                    listOf("substitute")
                )
            )
        )

        useCase
            .run("name")
            .test()
            .assertValue(
                IngredientSubstitutes(
                    listOf("substitute")
                )
            )
    }
}