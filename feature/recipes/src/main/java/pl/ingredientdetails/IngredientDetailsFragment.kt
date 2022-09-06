package pl.ingredientdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pl.Constants
import pl.architecture.base.BaseFragment
import pl.extensions.createFormattedText
import pl.recipes.R
import pl.recipes.databinding.IngredientDetailsFragmentBinding
import pl.uimodel.ingredientdetails.EstimatedCostUiModel
import pl.uimodel.ingredientdetails.IngredientFullDetailsUiModel

@AndroidEntryPoint
class IngredientDetailsFragment :
    BaseFragment<IngredientDetailsFragmentBinding>(IngredientDetailsFragmentBinding::inflate) {

    private val viewModel: IngredientDetailsViewModel by viewModels()
    private val arguments: IngredientDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProgressLoadingEvent()
        observeIngredientDetailsViewState()
        viewModel.getIngredientDetails(
            arguments.ingredientDetailsNavigationModel.id,
            arguments.ingredientDetailsNavigationModel.name
        )
    }

    private fun observeProgressLoadingEvent() {
        viewModel
            .getProgressLoadingEvent()
            .observe(viewLifecycleOwner) { visibility ->
                binding.animationView.isVisible = visibility
                binding.scrollView.isVisible = !visibility
            }
    }

    private fun observeIngredientDetailsViewState() {
        viewModel
            .getIngredientDetailsViewState()
            .observe(viewLifecycleOwner) { state ->
                when (state) {
                    is IngredientDetailsViewState.IngredientDetailsLoaded -> initView(state.result)
                    is IngredientDetailsViewState.IngredientDetailsLoadFailure -> {
                        Snackbar.make(
                            binding.root,
                            R.string.recipes_data_loading_error,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun initView(result: IngredientFullDetailsUiModel) {
        initIngredientImage(result.image)
        initIngredientName(result.name)
        initIngredientAmount(result.amount)
        initIngredientConsistency(result.consistency)
        initIngredientUnits(result.possibleUnits)
        initIngredientCategory(result.categoryPath)
        initIngredientEstimatedCost(result.estimateCost)
        initIngredientSubstitutes(result.substitutes)
    }

    private fun initIngredientImage(url: String?) {
        binding.ingredientImage.load(Constants.BASE_IMAGE_URL + url) {
            crossfade(600)
            error(pl.design.R.drawable.ic_error_placeholder)
        }
    }

    private fun initIngredientName(name: String?) {
        if (name.isNullOrEmpty()) {
            binding.ingredientName.isVisible = false
            return
        }
        binding.ingredientName.text = name
    }

    private fun initIngredientAmount(amount: Double?) {
        if (amount == null) {
            binding.amountText.isVisible = false
            return
        }
        binding.amountText.text =
            requireContext().resources.getString(R.string.amount_title, amount)
    }

    private fun initIngredientConsistency(consistency: String?) {
        if (consistency.isNullOrEmpty()) {
            binding.consistencyText.isVisible = false
            return
        }
        binding.consistencyText.text =
            requireContext().resources.getString(R.string.consistency_title, consistency)
    }

    private fun initIngredientUnits(units: List<String>?) {
        if (units.isNullOrEmpty()) {
            binding.unitsText.isVisible = false
            return
        }
        binding.unitsText.text =
            requireContext().resources.getString(
                R.string.ingredients_unit,
                units.createFormattedText()
            )
    }

    private fun initIngredientCategory(categories: List<String>?) {
        if (categories.isNullOrEmpty()) {
            binding.categoryText.isVisible = false
            return
        }
        binding.categoryText.text =
            requireContext().resources.getString(
                R.string.categories_title,
                categories.createFormattedText()
            )
    }

    private fun initIngredientEstimatedCost(estimatedCost: EstimatedCostUiModel?) {
        if (estimatedCost?.value == null && estimatedCost?.unit.isNullOrEmpty()) {
            binding.estimatedText.isVisible = false
            return
        }
        binding.estimatedText.text =
            requireContext().resources.getString(
                R.string.estimate_title,
                "Value:${estimatedCost?.value} ${estimatedCost?.unit}"
            )
    }

    private fun initIngredientSubstitutes(substitutes: List<String>?) {
        if (substitutes.isNullOrEmpty()) {
            binding.substitutesText.isVisible = false
            return
        }
        binding.substitutesText.text =
            requireContext().resources.getString(
                R.string.substitutes_title,
                substitutes.createFormattedText()
            )
    }
}