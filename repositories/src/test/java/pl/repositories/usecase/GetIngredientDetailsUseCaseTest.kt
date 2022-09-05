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
import pl.repositories.IngredientDetailsRepository
import pl.repositories.model.ingredientDetails
import pl.usecase.ingredientdetails.GetIngredientDetailsUseCase

class GetIngredientDetailsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: IngredientDetailsRepository = mock()
    private val useCase: GetIngredientDetailsUseCase = GetIngredientDetailsUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `return ingredient details when get ingredient details returns ingredient details`() {
        whenever(repository.getIngredientDetails(1)).thenReturn(Observable.just(ingredientDetails))

        useCase
            .run(1)
            .test()
            .assertValue(ingredientDetails)
    }
}