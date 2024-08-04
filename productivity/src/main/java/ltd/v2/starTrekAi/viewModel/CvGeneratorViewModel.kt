package ltd.v2.starTrekAi.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ltd.v2.starTrekAi.model.ExperienceModel
import ltd.v2.starTrekAi.model.ReferenceModel
import ltd.v2.starTrekAi.model.TemplateModel

class CvGeneratorViewModel : ViewModel() {

    // LiveData for various text fields
    private val _fullNameState = MutableLiveData<String>()
    val fullNameState: LiveData<String> = _fullNameState

    // Methods to update state
    fun setFullName(fullName: String) {
        _fullNameState.value = fullName
    }


    private val _contactNumberState = MutableLiveData<String>()
    val contactNumberState: LiveData<String> = _contactNumberState

    fun setContactNumber(contactNumber: String) {
        _contactNumberState.value = contactNumber
    }

    private val _emailAddressState = MutableLiveData<String>()
    val emailAddressState: LiveData<String> = _emailAddressState

    fun setEmailAddress(emailAddress: String) {
        _emailAddressState.value = emailAddress
    }

    private val _linkedinUrlState = MutableLiveData<String>()
    val linkedinUrlState: LiveData<String> = _linkedinUrlState

    fun setLinkedinUrl(linkedinUrl: String) {
        _linkedinUrlState.value = linkedinUrl
    }

    private val _physicalAddressState = MutableLiveData<String>()
    val physicalAddressState: LiveData<String> = _physicalAddressState

    fun setPhysicalAddress(physicalAddress: String) {
        _physicalAddressState.value = physicalAddress
    }

    // State for selected expertise and career objective
    private val _expertise = MutableLiveData<String>()
    val expertise: LiveData<String> = _expertise

    fun setExpertise(expertise: String) {
        _expertise.value = expertise
    }

    // Predefined lists
    private val _expertiseList = MutableLiveData<List<String>>()
    val expertiseList: LiveData<List<String>> = _expertiseList


    private val _selectedCareerObjective = MutableLiveData<String>()
    val selectedCareerObjective: LiveData<String> = _selectedCareerObjective

    private val _careerObjectiveList = MutableLiveData<List<String>>()
    val careerObjectiveList: LiveData<List<String>> = _careerObjectiveList

    fun setSelectedCareerObjective(careerObjective: String) {
        _selectedCareerObjective.value = careerObjective
    }

    fun getCareerObjectiveList(): List<String> {
        _careerObjectiveList.value = listOf(
            "Short & simple",
            "Moderate",
            "Elaborative & Creative"
        )

        return _careerObjectiveList.value!!
    }

    // State for selected expertise and career objective
    private val _isWorkExperience = MutableLiveData<String>()
    val isWorkExperience: LiveData<String> = _isWorkExperience

    private val _workExperienceList = MutableLiveData<List<String>>()
    fun setIsWorkExperience(isWorkExperience: String) {
        _isWorkExperience.value = isWorkExperience
    }


    private val _experienceList = MutableLiveData<MutableList<ExperienceModel>>().apply {
        value = mutableListOf(ExperienceModel(1, true))
    }
    val experienceListLiveData: LiveData<MutableList<ExperienceModel>> = _experienceList

    fun addExperience(experience: ExperienceModel) {
        _experienceList.value = _experienceList.value?.toMutableList()?.apply {
            add(experience)
        }
    }

    fun removeExperience(experience: ExperienceModel) {
        _experienceList.value = _experienceList.value?.toMutableList()?.apply {
            remove(experience)
        }
    }

    fun clearExperienceList() {
        _experienceList.value = mutableListOf()
        _experienceList.value = _experienceList.value // Trigger observers
        _experienceList.value?.add(ExperienceModel(id = 1, isExperienceBtnClicked = true))
        _experienceList.value = _experienceList.value // Trigger observers
    }

    fun getWorkExperienceList(): List<String> {
        _workExperienceList.value = listOf(
            "Yes",
            "No"
        )

        return _workExperienceList.value!!
    }


    fun getExpertiseList(): List<String> {
        _expertiseList.value = listOf(
            "Marketing Professional",
            "Software engineer",
            "Graphic designer",
            "Others"
        )

        return _expertiseList.value!!
    }


    private val _workExpertiseList = MutableLiveData<List<String>>()
    private val _selectedItemsWorkExpertise = MutableLiveData<List<String>>(emptyList())

    val workExpertiseList: LiveData<List<String>> = _workExpertiseList
    val selectedItemsWorkExpertise: LiveData<List<String>> = _selectedItemsWorkExpertise

    init {
        _workExpertiseList.value = listOf(
            "Prompt",
            "Adherence to deadlines",
            "Time management",
            "Adaptive",
            "Detail oriented"
        )
    }

    fun updateSelectedItemsWorkExpertise(items: List<String>) {
        _selectedItemsWorkExpertise.value = items
    }


    private val _selectedextendWorkExpertise = MutableLiveData<String>()
    val extendWorkExpertise: LiveData<String> = _selectedextendWorkExpertise

    private val _selectedextendWorkExpertiseList = MutableLiveData<List<String>>()
    val selectedextendWorkExpertiseList: LiveData<List<String>> = _selectedextendWorkExpertiseList

    fun setSelectedWorkExpertise(workExpertise: String) {
        _selectedextendWorkExpertise.value = workExpertise
    }

    fun getSelectedWorkExpertiseList(): List<String> {
        _selectedextendWorkExpertiseList.value = listOf(
            "Short & simple",
            "Moderate",
            "Elaborative & Creative"
        )

        return _selectedextendWorkExpertiseList.value!!
    }


    private val _selectedHightestEducation = MutableLiveData<String>()
    val selectedHightestEducation: LiveData<String> = _selectedHightestEducation

    private val _selectedHightestEducationList = MutableLiveData<List<String>>()
    val selectedHightestEducationList: LiveData<List<String>> = _selectedHightestEducationList

    fun setSelectedHighestEducation(heightestEducation: String) {
        _selectedHightestEducation.value = heightestEducation
    }

    fun getSelectedHighestEducationList(): List<String> {
        _selectedHightestEducationList.value = listOf(
            "Graduate",
            "Undergraduate",
            "HSC",
            "SSC"
        )

        return _selectedHightestEducationList.value!!
    }


    private val _schoolNameState = MutableLiveData<String>()
    val schoolNameState: LiveData<String> = _schoolNameState

    fun setAttendSchool(schoolName: String) {
        _schoolNameState.value = schoolName
    }

    private val _fromDateState = MutableLiveData("")
    val fromDateState: LiveData<String> = _fromDateState

    private val _toDateState = MutableLiveData("")
    val toDateState: LiveData<String> = _toDateState

    fun setFromDate(date: String) {
        _fromDateState.value = date
    }

    fun setToDate(date: String) {
        _toDateState.value = date
    }


    private val _selectedSscBackground = MutableLiveData<String>()
    val selectedSscBackground: LiveData<String> = _selectedSscBackground

    private val _SscBackgroundList = MutableLiveData<List<String>>()
    val sscBackgroundList: LiveData<List<String>> = _SscBackgroundList

    fun setSelectedSscBackground(sscBackground: String) {
        _selectedSscBackground.value = sscBackground
    }

    fun getSscBackgroundList(): List<String> {
        _SscBackgroundList.value = listOf(
            "Science",
            "Commerce",
            "Arts"
        )

        return sscBackgroundList.value!!
    }

    private val _sscResultState = MutableLiveData<String>()
    val sscResultState: LiveData<String> = _sscResultState

    fun setSscResult(sscResult: String) {
        _sscResultState.value = sscResult
    }

    private val _collegeAttendedState = MutableLiveData<String>()
    val collegeAttendedState: LiveData<String> = _collegeAttendedState

    fun setCollegeAttended(collegeName: String) {
        _collegeAttendedState.value = collegeName
    }


    private val _collegeEnrollmentFromDateState = MutableLiveData("")
    val collegeEnrollmentFromDateState: LiveData<String> = _collegeEnrollmentFromDateState

    private val _collegeEnrollmentToDateState = MutableLiveData("")
    val collegeEnrollmentToDateState: LiveData<String> = _collegeEnrollmentToDateState

    fun setCollegeEnrollmentFromDate(date: String) {
        _collegeEnrollmentFromDateState.value = date
    }

    fun setCollegeEnrollmentToDate(date: String) {
        _collegeEnrollmentToDateState.value = date
    }

    private val _selectedHscBackground = MutableLiveData<String>()
    val selectedHscBackground: LiveData<String> = _selectedHscBackground

    private val _hscBackgroundList = MutableLiveData<List<String>>()
    val hscBackgroundList: LiveData<List<String>> = _hscBackgroundList

    fun setSelectedHscBackground(hscBackground: String) {
        _selectedHscBackground.value = hscBackground
    }

    fun getHscBackgroundList(): List<String> {
        _hscBackgroundList.value = listOf(
            "Science",
            "Commerce",
            "Arts"
        )

        return hscBackgroundList.value!!
    }

    private val _hscResultState = MutableLiveData<String>()
    val hscResultState: LiveData<String> = _hscResultState

    fun setHscResult(hscResult: String) {
        _hscResultState.value = hscResult
    }

    private val _universityAttendedState = MutableLiveData<String>()
    val universityAttendedState: LiveData<String> = _universityAttendedState
    fun setUniversityAttended(universityName: String) {
        _universityAttendedState.value = universityName
    }

    private val _universityEnrollmentFromDateState = MutableLiveData<String>()
    val universityEnrollmentFromDateState: LiveData<String> = _universityEnrollmentFromDateState

    private val _universityEnrollmentToDateState = MutableLiveData<String>()
    val universityEnrollmentToDateState: LiveData<String> = _universityEnrollmentToDateState

    fun setUniversityEnrollmentFromDate(date: String) {
        _universityEnrollmentFromDateState.value = date
    }

    fun setUniversityEnrollmentToDate(date: String) {
        _universityEnrollmentToDateState.value = date
    }


    private val _selectedUndergradBackground = MutableLiveData<String>()
    val selectedUndergradBackground: LiveData<String> = _selectedUndergradBackground

    private val _undergradBackgroundList = MutableLiveData<List<String>>()
    val undergradBackgroundList: LiveData<List<String>> = _undergradBackgroundList

    fun setSelectedUndergradBackground(undergradBackground: String) {
        _selectedUndergradBackground.value = undergradBackground
    }

    fun getUndergradBackgroundList(): List<String> {
        _undergradBackgroundList.value = listOf(
            "BBA",
            "CSE",
            "EEE",
            "Others"
        )

        return undergradBackgroundList.value!!
    }

    private val _universityResultState = MutableLiveData<String>()
    val universityResultState: LiveData<String> = _universityResultState

    fun setUniversityResult(universityResult: String) {
        _universityResultState.value = universityResult
    }

    private val _postgraduationAttendedState = MutableLiveData<String>()
    val postgraduationAttendedState: LiveData<String> = _postgraduationAttendedState

    fun setPostgraduationAttended(postgraduationName: String) {
        _postgraduationAttendedState.value = postgraduationName
    }

    private val _postGraduationEnrollmentFromDateState = MutableLiveData<String>()
    val postGraduationEnrollmentFromDateState: LiveData<String> =
        _postGraduationEnrollmentFromDateState

    private val _postGraduationEnrollmentToDateState = MutableLiveData<String>()
    val postGraduationEnrollmentToDateState: LiveData<String> = _postGraduationEnrollmentToDateState

    fun setPostGraduationEnrollmentFromDate(date: String) {
        _postGraduationEnrollmentFromDateState.value = date
    }

    fun setPostGraduationEnrollmentToDate(date: String) {
        _postGraduationEnrollmentToDateState.value = date
    }

    private val _selectedPostgradBackground = MutableLiveData<String>()
    val selectedPostgradBackground: LiveData<String> = _selectedPostgradBackground

    private val _postgradBackgroundList = MutableLiveData<List<String>>()
    val postgradBackgroundList: LiveData<List<String>> = _postgradBackgroundList

    fun setSelectedPostgradBackground(postgradBackground: String) {
        _selectedPostgradBackground.value = postgradBackground
    }

    fun getPostgradBackgroundList(): List<String> {
        _postgradBackgroundList.value = listOf(
            "MBA",
            "MSc",
            "MPhil",
            "PhD",
            "Others"
        )

        return postgradBackgroundList.value!!
    }

    private val _postgraduationResultState = MutableLiveData<String>()
    val postgraduationResultState: LiveData<String> = _postgraduationResultState

    fun setPostgraduationResult(postgraduationResult: String) {
        _postgraduationResultState.value = postgraduationResult
    }


    private val _chooseSkillList = MutableLiveData<List<String>>()
    private val _selectedChooseSkillList = MutableLiveData<List<String>>(emptyList())

    val chooseSkillList: LiveData<List<String>> = _chooseSkillList
    val selectedChooseSkillList: LiveData<List<String>> = _selectedChooseSkillList

    init {
        _chooseSkillList.value = listOf(
            "Foreign language",
            "Programming languages",
            "Data analysis",
            "Project management methodologies",
            "Digital Marketing",
            "Graphic design software",
            "Financial modeling and analysis",
            "Web development",
            "Machine learning and AI",
        )
    }

    fun updateSelectedChooseSkillsetList(items: List<String>) {
        _selectedChooseSkillList.value = items
    }


    private val _selectedSkillExpertise = MutableLiveData<String>()
    val skillExpertise: LiveData<String> = _selectedSkillExpertise

    private val _selectedSkillExpertiseList = MutableLiveData<List<String>>()
    val selectedSkillExpertiseList: LiveData<List<String>> = _selectedSkillExpertiseList

    fun setSelectedSkillExpertise(skillExpertise: String) {
        _selectedSkillExpertise.value = skillExpertise
    }

    fun getSelectedSkillExpertiseList(): List<String> {
        _selectedSkillExpertiseList.value = listOf(
            "Short & simple",
            "Moderate",
            "Elaborative & Creative"
        )

        return _selectedSkillExpertiseList.value!!
    }


    private val _coCurrcularActivitiesList = MutableLiveData<List<String>>()
    private val _selectedCoCurrcularActivitiesList = MutableLiveData<List<String>>(emptyList())

    val coCurrcularActivitiesList: LiveData<List<String>> = _coCurrcularActivitiesList
    val selectedCoCurrcularActivitiesList: LiveData<List<String>> =
        _selectedCoCurrcularActivitiesList

    init {
        _coCurrcularActivitiesList.value = listOf(
            "Debate",
            "Academic competitions",
            "Arts & Creativity",
            "Community services",
            "Sports & athletics",
            "Others"
        )
    }

    fun updateCoCurricularActivitiesList(items: List<String>) {
        _selectedCoCurrcularActivitiesList.value = items
    }

    private val _selectedAddReference = MutableLiveData<String>()
    val selectedAddReference: LiveData<String> = _selectedAddReference

    private val _addReferenceList = MutableLiveData<List<String>>()
    val addReferenceList: LiveData<List<String>> = _addReferenceList

    fun setSelectedAddReference(addReference: String) {
        _selectedAddReference.value = addReference
    }

    fun getSelectedAddReferenceList(): List<String> {
        _addReferenceList.value = listOf(
            "Yes",
            "No"
        )

        return _addReferenceList.value!!
    }


    // For reference

    private val _referenceList = MutableLiveData<MutableList<ReferenceModel>>().apply {
        value = mutableListOf(ReferenceModel(1, true))
    }
    val referenceListLiveData: LiveData<MutableList<ReferenceModel>> = _referenceList

    fun addReference(reference: ReferenceModel) {
        _referenceList.value = _referenceList.value?.toMutableList()?.apply {
            add(reference)
        }
    }

    fun removeReference(reference: ReferenceModel) {
        _referenceList.value = _referenceList.value?.toMutableList()?.apply {
            remove(reference)
        }
    }

    fun clearReferenceList() {
        _referenceList.value = mutableListOf()
        _referenceList.value = _referenceList.value // Trigger observers
        _referenceList.value?.add(ReferenceModel(id = 1, isRefereceBtnClicked = true))
        _referenceList.value = _referenceList.value // Trigger observers
    }


    private val _templates = MutableLiveData<List<TemplateModel>>()
    val templates: LiveData<List<TemplateModel>> = _templates

    private val _currentSelctedtemplates = MutableLiveData<String>()
    val currentSelctedtemplates: LiveData<String> = _currentSelctedtemplates

    init {
        _templates.value = listOf(
            TemplateModel(
                tempId = 1,
                tempName = "Template 1",
                tempImg = "https://w7.pngwing.com/pngs/802/413/png-transparent-professional-cv-and-resume-template.png"
            ),
            TemplateModel(
                tempId = 2,
                tempName = "Template 2",
                tempImg = "https://w7.pngwing.com/pngs/122/915/png-transparent-simple-professional-cv-and-resume-template-thumbnail.png"
            ),
            TemplateModel(
                tempId = 3,
                tempName = "Template 3",
                tempImg = "https://i.pinimg.com/originals/b0/60/8e/b0608e7c3e0402e7f4a25f7e292f34a3.jpg"
            )
        )
    }


    fun setSelectedTemplate(selectedTemplate: TemplateModel) {
        val currentList = _templates.value?.map { template ->
            template.copy(isTempSelected = template.tempId == selectedTemplate.tempId)
        }
        _templates.value = currentList!!
        _currentSelctedtemplates.value = selectedTemplate.tempName
    }


    private val _selectedImageUri = MutableLiveData<Uri?>()
    val selectedImageUri: LiveData<Uri?> = _selectedImageUri

    fun setImageUri(uri: Uri?) {
        _selectedImageUri.value = uri
    }



}
