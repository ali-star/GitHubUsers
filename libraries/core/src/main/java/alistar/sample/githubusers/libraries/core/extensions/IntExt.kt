package alistar.sample.githubusers.libraries.core.extensions

fun Int.toQuantityString(valueTitle: String, valuesTitle: String): String = when {
    this >= OneK -> "${this / OneK}k $valuesTitle"
    this == 1 -> "1 $valueTitle"
    this == 0 -> "0 $valueTitle"
    else -> "$this $valuesTitle"
}

fun Int.toQuantityString(): String = when {
    this >= OneK -> "${this / OneK}k"
    else -> this.toString()
}

const val OneK = 1000
