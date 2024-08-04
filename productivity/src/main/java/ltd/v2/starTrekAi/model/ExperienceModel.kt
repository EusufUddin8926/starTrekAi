package ltd.v2.starTrekAi.model

data class ExperienceModel(
    val id: Int,
    var isExperienceBtnClicked: Boolean,
    var companyName: String = "",
    var jobTitle: String = "",
    var fromDate: String = "",
    var toDate: String = "",
) {
    override fun toString(): String {
        return "ExperienceModel(id=$id, isExperienceBtnClicked=$isExperienceBtnClicked, companyName='$companyName', jobTitle='$jobTitle', fromDate='$fromDate', toDate='$toDate')"
    }
}

