package pl.design

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import pl.design.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ErrorViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setErrorTextColor(color: Int) {
        binding.errorTextView.setTextColor(color)
    }

    fun setErrorText(text: String) {
        binding.errorTextView.text = text
    }
}