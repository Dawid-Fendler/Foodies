package pl.recipedetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import pl.recipes.databinding.ViewWinesBinding

class WineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ViewWinesBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setWineName(name: String) {
        binding.wineName.text = name
    }
}