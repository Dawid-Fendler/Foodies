package pl.ingredients

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.Constants.BASE_IMAGE_URL
import pl.recipes.databinding.IngredientsListRowViewBinding
import pl.uimodel.recipedetails.ExtendedIngredientUiModel

class IngredientsViewHolder(
    private val binding: IngredientsListRowViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: ExtendedIngredientUiModel, imageClickSubject: PublishSubject<Int>) {
        binding.ingredientName.text = result.name
        binding.ingredientUnit.text = result.unit
        binding.ingredientName.text = result.amount.toString()
        loadImageFromUrl(result.image)
        initImageOnClick(result, imageClickSubject)
    }

    private fun loadImageFromUrl(imageUrl: String) {
        binding.ingredientImageView.load(BASE_IMAGE_URL + imageUrl) {
            crossfade(600)
            error(pl.design.R.drawable.ic_error_placeholder)
        }
    }

    private fun initImageOnClick(
        result: ExtendedIngredientUiModel,
        imageClickSubject: PublishSubject<Int>
    ) {
        binding.root.setOnClickListener {
            imageClickSubject
                .onNext(result.id)
        }
    }

    companion object {
        fun from(parent: ViewGroup): IngredientsViewHolder =
            IngredientsViewHolder(
                IngredientsListRowViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}