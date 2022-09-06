package pl.extensions

import android.widget.TextView
import androidx.core.text.HtmlCompat

fun TextView.parseHtml(description: String) {
    text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
}