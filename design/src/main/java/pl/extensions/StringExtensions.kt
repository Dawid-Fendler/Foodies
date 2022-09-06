package pl.extensions

fun List<String>.createFormattedText(): String {
    var text = ""
    forEach { element ->
        if (element == last()) {
            text = "- $element"
            return@forEach
        }
        text = "-$element\n"
    }
    return text
}