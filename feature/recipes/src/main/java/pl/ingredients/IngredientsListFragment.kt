package pl.ingredients

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.plusAssign
import pl.architecture.base.BaseFragment
import pl.recipes.databinding.IngredientsListFragmentBinding
import pl.uimodel.recipedetails.ExtendedIngredientUiModel
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class IngredientsListFragment :
    BaseFragment<IngredientsListFragmentBinding>(IngredientsListFragmentBinding::inflate) {

    private val ingredientsAdapter by lazy { IngredientsAdapter() }
    private val arguments: IngredientsListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(arguments.ingredients)
        navigateToIngredientDetails()
    }

    private fun initRecyclerView(ingredients: Array<ExtendedIngredientUiModel>) {
        binding.ingredientsRecyclerView.adapter = ingredientsAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ingredientsAdapter.setData(ingredients.toList())
    }

    private fun navigateToIngredientDetails() {
        compositeDisposable += ingredientsAdapter.getImageClickSubject()
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .subscribe { ingredientDetailsNavigationModel ->
                findNavController().navigate(
                    IngredientsListFragmentDirections
                        .actionIngredientsListFragmentToIngredientDetailsFragment(
                            ingredientDetailsNavigationModel
                        )
                )
            }
    }
}