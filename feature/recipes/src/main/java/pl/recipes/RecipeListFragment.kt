package pl.recipes

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.plusAssign
import pl.architecture.base.BaseFragment
import pl.recipes.databinding.RecipeListFragmentBinding
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class RecipeListFragment :
    BaseFragment<RecipeListFragmentBinding>(RecipeListFragmentBinding::inflate) {

    private val viewModel: RecipeListViewModel by viewModels()

    private val mAdapter by lazy { RecipesAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeRefreshLayout()
        observeProgressLoadingEvent()
        observeRecipeListViewState()
        navigateToRecipeDetails()
        viewModel.init()

    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.init()
        }
    }

    private fun observeProgressLoadingEvent() {
        viewModel
            .getProgressLoadingEvent()
            .observe(viewLifecycleOwner) { isProgressVisible ->
                binding.swipeRefreshLayout.isRefreshing = isProgressVisible
            }
    }

    private fun observeRecipeListViewState() {
        viewModel
            .getRecipesListViewState()
            .observe(viewLifecycleOwner) { state ->
                when (state) {
                    is RecipeListViewState.RecipesListLoaded ->
                        mAdapter.setData(state.result)
                    is RecipeListViewState.RecipesEmpty -> {
                        binding.recyclerView.isVisible = false
                        binding.errorView.isVisible = true
                        binding.errorView.setErrorText(state.errorMessage)
                    }
                    is RecipeListViewState.RecipeListLoadFailure -> {
                        Snackbar.make(
                            binding.root,
                            R.string.recipes_data_loading_error,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun navigateToRecipeDetails() {
        compositeDisposable += mAdapter.getImageClickSubject()
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .subscribe {
                findNavController().navigate(
                    RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailsFragment(it)
                )
            }
    }
}