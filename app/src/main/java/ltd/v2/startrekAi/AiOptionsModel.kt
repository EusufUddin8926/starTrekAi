package ltd.v2.startrekAi


data class AiOptionsModel(
    var id: Int,
    var imagePath: Int,
    var menuTitle: String
)

fun getAiOptions(): List<AiOptionsModel> {
    return listOf(
        AiOptionsModel(1, R.drawable.grammar, "Grammar Check"),
        AiOptionsModel(2, R.drawable.response, "Response Generator"),
        AiOptionsModel(3, R.drawable.resume, "CV Generator"),
        AiOptionsModel(4, R.drawable.swap, "Face Swap"),
        AiOptionsModel(5, R.drawable.abbreviation, "Summarizer")
    )
}
