package pl.ingredients

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.recipes.databinding.IngredientsListFragmentBinding
import pl.uimodel.recipedetails.ExtendedIngredientUiModel

@AndroidEntryPoint
class IngredientsListFragment :
    BaseFragment<IngredientsListFragmentBinding>(IngredientsListFragmentBinding::inflate) {

    private val ingredientsAdapter by lazy { IngredientsAdapter() }
    private val arguments: IngredientsListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(arguments.ingredients)
    }

    private fun initRecyclerView(ingredients: Array<ExtendedIngredientUiModel>) {
        binding.ingredientsRecyclerView.adapter = ingredientsAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ingredientsAdapter.setData(ingredients.toList())
    }
}