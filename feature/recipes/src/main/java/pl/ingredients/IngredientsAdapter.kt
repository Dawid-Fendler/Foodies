package pl.ingredients

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.ingredientdetails.IngredientDetailsNavigationModel
import pl.uimodel.recipedetails.ExtendedIngredientUiModel

class IngredientsAdapter : RecyclerView.Adapter<IngredientsViewHolder>() {

    private var ingredients = emptyList<ExtendedIngredientUiModel>()

    private val imageClickSubject: PublishSubject<IngredientDetailsNavigationModel> = PublishSubject.create()
    fun getImageClickSubject() = imageClickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentIngredient = ingredients[position]
        holder.bind(currentIngredient, imageClickSubject)
    }

    override fun getItemCount() = ingredients.size

    fun setData(newData: List<ExtendedIngredientUiModel>) {
        val recipesDiffUtil = IngredientDiffUtil(ingredients, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        ingredients = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class IngredientDiffUtil<T>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] === newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}