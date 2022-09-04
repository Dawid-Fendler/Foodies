package pl.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.Constants.BASE_IMAGE_URL
import pl.ingredientdetails.IngredientDetailsNavigationModel
import pl.recipes.databinding.IngredientsListRowViewBinding
import pl.uimodel.recipedetails.ExtendedIngredientUiModel

class IngredientsViewHolder(
    private val binding: IngredientsListRowViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        result: ExtendedIngredientUiModel,
        imageClickSubject: PublishSubject<IngredientDetailsNavigationModel>
    ) {
        binding.ingredientName.text = result.name
        binding.ingredientUnit.text = result.unit
        binding.ingredientName.text = result.amount.toString()
        loadImageFromUrl(result.image)
        initImageOnClick(result.id, result.name, imageClickSubject)
    }

    private fun loadImageFromUrl(imageUrl: String) {
        binding.ingredientImageView.load(BASE_IMAGE_URL + imageUrl) {
            crossfade(600)
            error(pl.design.R.drawable.ic_error_placeholder)
        }
    }

    private fun initImageOnClick(
        id: Int,
        name: String,
        imageClickSubject: PublishSubject<IngredientDetailsNavigationModel>
    ) {
        binding.root.setOnClickListener {
            imageClickSubject
                .onNext(IngredientDetailsNavigationModel(id = id, name = name))
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