package pl.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.recipes.databinding.RecipeListRowViewBinding
import pl.uimodel.recipes.RecipeResultUiModel

class RecipesViewHolder(
    private val binding: RecipeListRowViewBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(result: RecipeResultUiModel, imageClickSubject: PublishSubject<Int>) {
        binding.title.text = result.title
        loadImageFromUrl(result.image)
        initImageOnClick(result, imageClickSubject)
    }

    private fun loadImageFromUrl(imageUrl: String) {
        binding.image.load(imageUrl) {
            crossfade(600)
            error(pl.design.R.drawable.ic_error_placeholder)
        }
    }

    private fun initImageOnClick(
        result: RecipeResultUiModel,
        imageClickSubject: PublishSubject<Int>
    ) {
        binding.root.setOnClickListener {
            imageClickSubject
                .onNext(result.recipeId)
        }
    }

    companion object {
        fun from(parent: ViewGroup): RecipesViewHolder =
            RecipesViewHolder(
                RecipeListRowViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}