package pl.design

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import pl.design.databinding.ViewErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ViewErrorBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setErrorText(text: String) {
        binding.errorTextView.text = text
    }
}