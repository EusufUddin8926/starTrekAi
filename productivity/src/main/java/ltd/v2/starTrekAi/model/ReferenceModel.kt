package ltd.v2.starTrekAi.model

data class ReferenceModel(
    val id: Int,
    var isRefereceBtnClicked: Boolean,
    var fullName: String = "",
    var designation: String = "",
    var companyName: String = "",
    var email: String = "",
)
