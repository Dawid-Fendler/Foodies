package pl.recipes

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.uimodel.recipes.RecipeResultUiModel
import pl.uimodel.recipes.RecipeUiModel

class RecipesAdapter : RecyclerView.Adapter<RecipesViewHolder>() {

    private var recipes = emptyList<RecipeResultUiModel>()

    private val imageClickSubject: PublishSubject<Int> = PublishSubject.create()
    fun getImageClickSubject() = imageClickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipes = recipes[position]
        holder.bind(currentRecipes, imageClickSubject)
    }

    override fun getItemCount() = recipes.size

    fun setData(newData: RecipeUiModel) {
        val recipesDiffUtil = RecipeDiffUtil(recipes, newData.recipeList)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.recipeList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class RecipeDiffUtil<T>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}