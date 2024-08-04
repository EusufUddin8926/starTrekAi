package ltd.v2.starTrekAi.util

fun countWords(text: String): Int {
    return text.split("\\s+".toRegex()).filter { it.isNotEmpty() }.size
}