package ltd.v2.starTrekAi.model

data class TemplateModel(
    val tempId: Int,
    val tempName: String,
    val tempImg: String, // Assuming this is a URL or resource identifier
    var isTempSelected: Boolean = false
)
