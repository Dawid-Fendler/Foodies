package pl.recipedetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.extensions.parseHtml
import pl.recipes.R
import pl.recipes.databinding.FragmentRecipeDetailsBinding
import pl.uimodel.recipedetails.ExtendedIngredientUiModel
import pl.uimodel.recipedetails.RecipeDetailsUiModel
import pl.uimodel.recipedetails.WinePairingUiModel

@AndroidEntryPoint
class RecipeDetailsFragment :
    BaseFragment<FragmentRecipeDetailsBinding>(FragmentRecipeDetailsBinding::inflate) {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private val arguments: RecipeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProgressLoadingEvent()
        observeRecipeListViewState()
        viewModel.init(arguments.recipeId)
    }

    private fun observeProgressLoadingEvent() {
        viewModel
            .getProgressLoadingEvent()
            .observe(viewLifecycleOwner) { visibility ->
                binding.animationView.isVisible = visibility
                binding.scrollView.isVisible = !visibility
            }
    }

    private fun observeRecipeListViewState() {
        viewModel
            .getRecipeDetailsViewState()
            .observe(viewLifecycleOwner) { state ->
                when (state) {
                    is RecipeDetailsViewState.RecipeDetailsLoaded ->
                        initView(state.result)
                    is RecipeDetailsViewState.RecipeDetailsLoadFailure ->
                        Snackbar.make(
                            binding.root,
                            R.string.recipe_details_data_loading_error,
                            Snackbar.LENGTH_LONG
                        ).show()
                }
            }
    }

    private fun initView(result: RecipeDetailsUiModel) {
        initRecipeImage(result.image)
        initTitle(result.title)
        initDishTypes(result.dishTypes)
        initDietTypes(result.dietTypes)
        initSummary(result.summary)
        initLikesValue(result.likes)
        initMinutesValue(result.minutes)
        initOpenWinesScreenButton(result.winePairing)
        initOpenIngredientsScreenButton(result.ingredients)
    }

    private fun initRecipeImage(imageUrl: String) {
        if (imageUrl.isEmpty()) {
            binding.recipeImage.isVisible = false
        }
        binding.recipeImage.load(imageUrl) {
            crossfade(600)
            error(pl.design.R.drawable.ic_error_placeholder)
        }
    }

    private fun initTitle(title: String) {
        binding.titleTextView.text = title
    }

    private fun initDishTypes(dishTypes: List<String>) {
        for (i in dishTypes) {
            binding.dishesTypeContainer.addView(
                DishesDietTypeView(requireContext()).apply {
                    setIcon(R.drawable.ic_check)
                    setTitle(i)
                    setTitleColor(pl.design.R.color.green)
                    setBackgroundTitleColor(pl.design.R.color.darker)
                }
            )
        }
        binding.dishesTypeTitle.isVisible = binding.dishesTypeContainer.isNotEmpty()
    }

    private fun initDietTypes(dietType: List<String>) {
        for (i in dietType) {
            binding.dietTypeContainer.addView(
                DishesDietTypeView(requireContext()).apply {
                    setIcon(R.drawable.ic_food)
                    setTitle(i)
                    setTitleColor(pl.design.R.color.green)
                    setBackgroundTitleColor(pl.design.R.color.darker)
                }
            )
        }
        binding.dietTypeTitle.isVisible = binding.dietTypeContainer.isNotEmpty()
    }

    private fun initSummary(description: String) {
        if (description.isEmpty()) {
            binding.summaryTextView.isVisible = false
        }
        binding.summaryTextView.parseHtml(description)
    }

    private fun initLikesValue(likes: Int) {
        binding.likesValue.text = likes.toString()
    }

    private fun initMinutesValue(minutes: Int) {
        binding.timeValue.text = minutes.toString()
    }

    private fun initOpenWinesScreenButton(winePairing: WinePairingUiModel) {
        if (winePairing.wines.isEmpty()) {
            return
        }
        binding.openWinesScreenButton.setOnClickListener {
            findNavController().navigate(
                RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToWinesFragmentDialog(
                    winePairing
                )
            )
        }
    }

    private fun initOpenIngredientsScreenButton(ingredients: List<ExtendedIngredientUiModel>) {
        if (ingredients.isEmpty()) {
            return
        }
        binding.openIngredientsScreenButton.setOnClickListener {
            findNavController().navigate(
                RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToIngredientsListFragment(
                    ingredients.toTypedArray()
                )
            )
        }
    }
}