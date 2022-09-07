package pl.recipedetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import pl.recipes.databinding.ViewDishesDietTypeBinding

class DishesDietTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {


    private val binding = ViewDishesDietTypeBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setIcon(icon: Int) {
        binding.iconMark.setImageResource(icon)
    }

    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun setTitleColor(color: Int) {
        binding.title.setTextColor(context.resources.getColor(color))
    }

    fun setBackgroundTitleColor(color: Int) {
        binding.title.setBackgroundColor(context.resources.getColor(color))
    }
}