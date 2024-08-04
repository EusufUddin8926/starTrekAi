package ltd.v2.starTrekAi.view.screen


import DatePickerField
import DynamicAutoSuggestionTextField
import DynamicChipGroupCompose
import ImagePickerCompose
import TemplateGrid
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import es.dmoral.toasty.Toasty
import ltd.v2.starTrekAi.R
import ltd.v2.starTrekAi.model.ExperienceModel
import ltd.v2.starTrekAi.model.ReferenceModel
import ltd.v2.starTrekAi.ui.theme.StarTrekAITheme
import ltd.v2.starTrekAi.view.components.DynamicAutoCompleteTextFieldChip
import ltd.v2.starTrekAi.view.components.DynamicOutlinedTextField
import ltd.v2.starTrekAi.view.components.ShowDynamicExperience
import ltd.v2.starTrekAi.view.components.ShowDynamicReferences
import ltd.v2.starTrekAi.viewModel.CvGeneratorViewModel
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CVGeneratorScreen(
    navController: NavHostController,
    cvGeneratorViewModel: CvGeneratorViewModel
) {
    val context = LocalContext.current
    val fullNameText by cvGeneratorViewModel.fullNameState.observeAsState(initial = "")
    val contactNumberText by cvGeneratorViewModel.contactNumberState.observeAsState(initial = "")
    val emailAddressText by cvGeneratorViewModel.emailAddressState.observeAsState(initial = "")
    val linkedinUrlText by cvGeneratorViewModel.linkedinUrlState.observeAsState(initial = "")
    val physicalAddressText by cvGeneratorViewModel.physicalAddressState.observeAsState(initial = "")
    val expertiseText by cvGeneratorViewModel.expertise.observeAsState(initial = "")
    val selectedcareerObjectiveText by cvGeneratorViewModel.selectedCareerObjective.observeAsState(
        initial = ""
    )
    val isWorkingExperience by cvGeneratorViewModel.isWorkExperience.observeAsState(initial = "")
    val selectedItems by cvGeneratorViewModel.selectedItemsWorkExpertise.observeAsState(emptyList())
    val selectedExtendWorkExpertise by cvGeneratorViewModel.extendWorkExpertise.observeAsState(
        initial = ""
    )
    val selectedHightestEducation by cvGeneratorViewModel.selectedHightestEducation.observeAsState(
        initial = ""
    )
    val schoolNameText by cvGeneratorViewModel.schoolNameState.observeAsState(initial = "")
    val fromDateText by cvGeneratorViewModel.fromDateState.observeAsState(initial = "")
    val toDateText by cvGeneratorViewModel.toDateState.observeAsState(initial = "")
    val selectedSscBackground by cvGeneratorViewModel.selectedSscBackground.observeAsState(initial = "")
    val sscResultText by cvGeneratorViewModel.sscResultState.observeAsState(initial = "")
    val collegeAttendedText by cvGeneratorViewModel.collegeAttendedState.observeAsState(initial = "")
    val collegeEnrollmentfromDateText by cvGeneratorViewModel.collegeEnrollmentFromDateState.observeAsState(
        initial = ""
    )
    val collegeEnrollmenttoDateText by cvGeneratorViewModel.collegeEnrollmentToDateState.observeAsState(
        initial = ""
    )
    val selectedHscBackground by cvGeneratorViewModel.selectedHscBackground.observeAsState(initial = "")
    val hscResultText by cvGeneratorViewModel.hscResultState.observeAsState(initial = "")
    val universityAttendedText by cvGeneratorViewModel.universityAttendedState.observeAsState(
        initial = ""
    )
    val universityEnrollmentfromDateText by cvGeneratorViewModel.universityEnrollmentFromDateState.observeAsState(
        initial = ""
    )
    val universityEnrollmenttoDateText by cvGeneratorViewModel.universityEnrollmentToDateState.observeAsState(
        initial = ""
    )
    val selectedUndergradeBackground by cvGeneratorViewModel.selectedUndergradBackground.observeAsState(
        initial = ""
    )
    val universityResultText by cvGeneratorViewModel.universityResultState.observeAsState(initial = "")
    val postgraduationAttendedText by cvGeneratorViewModel.postgraduationAttendedState.observeAsState(
        initial = ""
    )
    val postgraduationFromDateText by cvGeneratorViewModel.postGraduationEnrollmentFromDateState.observeAsState(
        initial = ""
    )
    val postgraduationToDateText by cvGeneratorViewModel.postGraduationEnrollmentToDateState.observeAsState(
        initial = ""
    )
    val selectedPostGraduationBackground by cvGeneratorViewModel.selectedPostgradBackground.observeAsState(
        initial = ""
    )
    val postgraduationResultText by cvGeneratorViewModel.postgraduationResultState.observeAsState(
        initial = ""
    )
    val selectedChooseSkillList by cvGeneratorViewModel.selectedChooseSkillList.observeAsState(
        emptyList()
    )
    val selectedSkillExpertise by cvGeneratorViewModel.skillExpertise.observeAsState(initial = "")
    val selectedCoCurricularActivitiesList by cvGeneratorViewModel.selectedCoCurrcularActivitiesList.observeAsState(
        emptyList()
    )
    val selectedAddReference by cvGeneratorViewModel.selectedAddReference.observeAsState(initial = "")
    val templates by cvGeneratorViewModel.templates.observeAsState(emptyList())
    val currentSelectedTemplate by cvGeneratorViewModel.currentSelctedtemplates.observeAsState(
        initial = ""
    )
    val selectedImageUri by cvGeneratorViewModel.selectedImageUri.observeAsState()


    var haveWorkExperience: Boolean
    if (isWorkingExperience == "Yes") {
        haveWorkExperience = true
    } else {
        haveWorkExperience = false
    }
    var isAddReference: Boolean
    if (selectedAddReference == "Yes") {
        isAddReference = true
    } else {
        isAddReference = false
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (context is Activity) {
                                context.finish()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",

                            )
                    }
                },
                title = {
                    Text(
                        stringResource(R.string.title_activity_cvgenerator_screen_ai),
                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,

                    )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.full_name_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = fullNameText,
                    onValueChange = {
                        cvGeneratorViewModel.setFullName(it)
                    },
                    placeholderResId = R.string.full_name,
                    keyboardType = KeyboardType.Text
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.contact_number_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = contactNumberText,
                    onValueChange = {
                        cvGeneratorViewModel.setContactNumber(it)
                    },
                    placeholderResId = R.string.contact_number,
                    keyboardType = KeyboardType.Number
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.share_email_address_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = emailAddressText,
                    onValueChange = {
                        cvGeneratorViewModel.setEmailAddress(it)
                    },
                    placeholderResId = R.string.share_email_address,
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.linkdin_profile_url_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = linkedinUrlText,
                    onValueChange = {
                        cvGeneratorViewModel.setLinkedinUrl(it)
                    },
                    placeholderResId = R.string.linkdin_profile_url,
                    keyboardType = KeyboardType.Uri
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.physical_address_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = physicalAddressText,
                    onValueChange = {
                        cvGeneratorViewModel.setPhysicalAddress(it)
                    },
                    placeholderResId = R.string.physical_address,
                    keyboardType = KeyboardType.Text
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.career_objective_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicAutoSuggestionTextField(
                    cvGeneratorViewModel.getExpertiseList(),
                    expertiseText,
                    {
                        cvGeneratorViewModel.setExpertise(it)
                    },
                    "Expertise or professional"
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.want_to_add_details_to_your_career_objective),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getCareerObjectiveList(),
                    selectedChip = selectedcareerObjectiveText,
                    onChipSelected = { cvGeneratorViewModel.setSelectedCareerObjective(it) },
                    modifier = Modifier
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.work_experience_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getWorkExperienceList(),
                    selectedChip = isWorkingExperience,
                    onChipSelected = {
                        if (it == "Yes") {
                            haveWorkExperience = true
                        } else {
                            haveWorkExperience = false
                            cvGeneratorViewModel.clearExperienceList()
                        }
                        cvGeneratorViewModel.setIsWorkExperience(it)
                    },
                    modifier = Modifier
                )

                if (haveWorkExperience) {
                    val context = LocalContext.current
                    ShowDynamicExperience(
                        cvGeneratorViewModel = cvGeneratorViewModel,
                        experienceList = cvGeneratorViewModel.experienceListLiveData,
                        onAddExperienceClick = { experience ->
                            experience.isExperienceBtnClicked = false
                            cvGeneratorViewModel.addExperience(
                                ExperienceModel(
                                    cvGeneratorViewModel.experienceListLiveData.value!!.size + 1,
                                    true, "", "", ""
                                )
                            )

                        },
                        onRemoveExperienceClick = { experience ->
                            if (cvGeneratorViewModel.experienceListLiveData.value!!.size == 1) {
                                Toasty.warning(
                                    context,
                                    "You can't remove last experience item",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@ShowDynamicExperience
                            }
                            cvGeneratorViewModel.removeExperience(experience)
                            cvGeneratorViewModel.experienceListLiveData.value!![cvGeneratorViewModel.experienceListLiveData.value!!.size - 1].isExperienceBtnClicked =
                                true
                        }
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.detail_your_work_expertisie_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DynamicAutoCompleteTextFieldChip(
                    hint = "Work Expertise",
                    items = cvGeneratorViewModel.workExpertiseList.value!!,
                    selectedItems = selectedItems,
                    onSelectedItemsChange = { updatedItems ->
                        cvGeneratorViewModel.updateSelectedItemsWorkExpertise(updatedItems)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.add_detail_your_work_expertisie_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getSelectedWorkExpertiseList(),
                    selectedChip = selectedExtendWorkExpertise,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedWorkExpertise(it)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.highest_level_of_education_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getSelectedHighestEducationList(),
                    selectedChip = selectedHightestEducation,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedHighestEducation(it)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_school_you_attended_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = schoolNameText,
                    onValueChange = {
                        cvGeneratorViewModel.setAttendSchool(it)
                    },
                    placeholderResId = R.string.school_you_attended,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_school_you_attended_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DatePickerField(
                    context = LocalContext.current,
                    fromDateState = fromDateText,
                    toDateState = toDateText,
                    onFromDateSelected = { date -> cvGeneratorViewModel.setFromDate(date) },
                    onToDateSelected = { date -> cvGeneratorViewModel.setToDate(date) }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_ssc_background_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getSscBackgroundList(),
                    selectedChip = selectedSscBackground,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedSscBackground(it)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_ssc_result_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = sscResultText,
                    onValueChange = {
                        cvGeneratorViewModel.setSscResult(it)
                    },
                    placeholderResId = R.string.mention_your_ssc_result,
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_college_name_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = collegeAttendedText,
                    onValueChange = {
                        cvGeneratorViewModel.setCollegeAttended(it)
                    },
                    placeholderResId = R.string.college_you_attended,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.college_enrolment_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DatePickerField(
                    context = LocalContext.current,
                    fromDateState = collegeEnrollmentfromDateText,
                    toDateState = collegeEnrollmenttoDateText,
                    onFromDateSelected = { date ->
                        cvGeneratorViewModel.setCollegeEnrollmentFromDate(
                            date
                        )
                    },
                    onToDateSelected = { date ->
                        cvGeneratorViewModel.setCollegeEnrollmentToDate(
                            date
                        )
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.hsc_background_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getHscBackgroundList(),
                    selectedChip = selectedHscBackground,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedHscBackground(it)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.hsc_result_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = hscResultText,
                    onValueChange = {
                        cvGeneratorViewModel.setHscResult(it)
                    },
                    placeholderResId = R.string.hsc_result,
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_university_name_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = universityAttendedText,
                    onValueChange = {
                        cvGeneratorViewModel.setUniversityAttended(it)
                    },
                    placeholderResId = R.string.university_you_attended,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_university_enrolment_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DatePickerField(
                    context = LocalContext.current,
                    fromDateState = universityEnrollmentfromDateText,
                    toDateState = universityEnrollmenttoDateText,
                    onFromDateSelected = { date ->
                        cvGeneratorViewModel.setUniversityEnrollmentFromDate(
                            date
                        )
                    },
                    onToDateSelected = { date ->
                        cvGeneratorViewModel.setUniversityEnrollmentToDate(
                            date
                        )
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_undergraduate_background_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getUndergradBackgroundList(),
                    selectedChip = selectedUndergradeBackground,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedUndergradBackground(it)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.mention_your_cgpa_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = universityResultText,
                    onValueChange = {
                        cvGeneratorViewModel.setUniversityResult(it)
                    },
                    placeholderResId = R.string.mention_your_cgpa,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.post_graduation_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = postgraduationAttendedText,
                    onValueChange = {
                        cvGeneratorViewModel.setPostgraduationAttended(it)
                    },
                    placeholderResId = R.string.post_graduation_attended,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.post_graduation_enrolment_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DatePickerField(
                    context = LocalContext.current,
                    fromDateState = postgraduationFromDateText,
                    toDateState = postgraduationToDateText,
                    onFromDateSelected = { date ->
                        cvGeneratorViewModel.setPostGraduationEnrollmentFromDate(
                            date
                        )
                    },
                    onToDateSelected = { date ->
                        cvGeneratorViewModel.setPostGraduationEnrollmentToDate(
                            date
                        )
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.post_graduation_background_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getPostgradBackgroundList(),
                    selectedChip = selectedPostGraduationBackground,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedPostgradBackground(it)
                    },
                    modifier = Modifier
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.post_graduation_result_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicOutlinedTextField(
                    value = postgraduationResultText,
                    onValueChange = {
                        cvGeneratorViewModel.setPostgraduationResult(it)
                    },
                    placeholderResId = R.string.post_graduation_result,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.choose_your_skills_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DynamicAutoCompleteTextFieldChip(
                    hint = "Choose your skills",
                    items = cvGeneratorViewModel.chooseSkillList.value!!,
                    selectedItems = selectedChooseSkillList,
                    onSelectedItemsChange = { updatedItems ->
                        cvGeneratorViewModel.updateSelectedChooseSkillsetList(updatedItems)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.add_details_to_your_skill_expertise_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getSelectedSkillExpertiseList(),
                    selectedChip = selectedSkillExpertise,
                    onChipSelected = {
                        cvGeneratorViewModel.setSelectedSkillExpertise(it)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.co_curricular_activities_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                DynamicAutoCompleteTextFieldChip(
                    hint = "Co-curricular activities",
                    items = cvGeneratorViewModel.coCurrcularActivitiesList.value!!,
                    selectedItems = selectedCoCurricularActivitiesList,
                    onSelectedItemsChange = { updatedItems ->
                        cvGeneratorViewModel.updateCoCurricularActivitiesList(updatedItems)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.references_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                DynamicChipGroupCompose(
                    chipList = cvGeneratorViewModel.getSelectedAddReferenceList(),
                    selectedChip = selectedAddReference,
                    onChipSelected = {
                        if (selectedAddReference == "Yes") {
                            isAddReference = true
                        } else {
                            isAddReference = false
                            cvGeneratorViewModel.clearReferenceList()
                        }
                        cvGeneratorViewModel.setSelectedAddReference(it)
                    },
                    modifier = Modifier
                )

                if (isAddReference) {
                    val context = LocalContext.current
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.add_references_title),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                    ShowDynamicReferences(
                        referenceList = cvGeneratorViewModel.referenceListLiveData,
                        onAddReferenceClick = { reference ->
                            reference.isRefereceBtnClicked = false
                            cvGeneratorViewModel.addReference(
                                ReferenceModel(
                                    cvGeneratorViewModel.referenceListLiveData.value!!.size + 1,
                                    true, "", "", "", ""
                                )
                            )
                        },
                        onRemoveReferenceClick = { reference ->
                            if (cvGeneratorViewModel.referenceListLiveData.value!!.size == 1) {
                                Toasty.warning(
                                    context,
                                    "You can't remove last reference item",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@ShowDynamicReferences
                            }
                            cvGeneratorViewModel.removeReference(reference)
                            cvGeneratorViewModel.referenceListLiveData.value!![cvGeneratorViewModel.referenceListLiveData.value!!.size - 1].isRefereceBtnClicked =
                                true
                        }
                    )

                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.available_template_options_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                TemplateGrid(templates = templates) { selectedTemplate ->
                    cvGeneratorViewModel.setSelectedTemplate(selectedTemplate)
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.upload_image_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                var context = LocalContext.current
                ImagePickerCompose(selectedImageUri = selectedImageUri, onImageSelected = {
                    cvGeneratorViewModel.setImageUri(Uri.parse(getFilePathFromUri(context, it)))
                })

                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            submitData(cvGeneratorViewModel, haveWorkExperience, isAddReference)
                        },
                        modifier = Modifier
                            .widthIn(min = 120.dp)
                            .heightIn(min = 44.dp)
                    ) {
                        Text(
                            text = "Submit",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }


            }
        }
    }
}


fun getFilePathFromUri(context: Context, uri: Uri): String? {
    val returnCursor = context.contentResolver.query(uri, null, null, null, null)
    returnCursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = it.getColumnIndex(OpenableColumns.SIZE)
        it.moveToFirst()
        val name = it.getString(nameIndex)
        val size = it.getLong(sizeIndex)
        val file = File(context.cacheDir, name)
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            return file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return null
}

fun submitData(
    cvGeneratorViewModel: CvGeneratorViewModel,
    haveWorkExperience: Boolean,
    isAddReference: Boolean
) {
    val dataMap = mutableMapOf<String, Any?>()

    cvGeneratorViewModel.fullNameState.value?.let { dataMap["fullName"] = it }
    cvGeneratorViewModel.contactNumberState.value?.let { dataMap["contactNumber"] = it }
    cvGeneratorViewModel.emailAddressState.value?.let { dataMap["emailAddress"] = it }
    cvGeneratorViewModel.linkedinUrlState.value?.let { dataMap["linkedinUrl"] = it }
    cvGeneratorViewModel.physicalAddressState.value?.let { dataMap["physicalAddress"] = it }
    cvGeneratorViewModel.expertise.value?.let { dataMap["expertise"] = it }
    cvGeneratorViewModel.selectedCareerObjective.value?.let {
        dataMap["selectedCareerObjective"] = it
    }
    cvGeneratorViewModel.isWorkExperience.value?.let { dataMap["isWorkExperience"] = it }
    if (haveWorkExperience) {
        val experienceMapList = mutableListOf<Map<String, Any?>>()
        for (experienceModel in cvGeneratorViewModel.experienceListLiveData.value!!) {
            val experienceMap = mutableMapOf<String, Any?>()
            experienceModel.id.let { experienceMap["ex_id"] = it }
            experienceModel.companyName.let { experienceMap["ex_company_name"] = it }
            experienceModel.jobTitle.let { experienceMap["ex_job_title"] = it }
            experienceModel.fromDate.let { experienceMap["ex_from_date"] = it }
            experienceModel.toDate.let { experienceMap["ex_to_date"] = it }
            experienceMapList.add(experienceMap)
        }
        experienceMapList.let { dataMap["experience"] = experienceMapList }
        cvGeneratorViewModel.selectedItemsWorkExpertise.value?.let {
            dataMap["selectedItemsWorkExpertise"] = it
        }
        cvGeneratorViewModel.extendWorkExpertise.value?.let { dataMap["extendWorkExpertise"] = it }
        cvGeneratorViewModel.selectedHightestEducation.value?.let {
            dataMap["selectedHightestEducation"] = it
        }
        cvGeneratorViewModel.schoolNameState.value?.let { dataMap["schoolName"] = it }
        cvGeneratorViewModel.fromDateState.value?.let { dataMap["fromDate"] = it }
        cvGeneratorViewModel.toDateState.value?.let { dataMap["toDate"] = it }
        cvGeneratorViewModel.selectedSscBackground.value?.let {
            dataMap["selectedSscBackground"] = it
        }
        cvGeneratorViewModel.sscResultState.value?.let { dataMap["sscResult"] = it }
        cvGeneratorViewModel.collegeAttendedState.value?.let {
            dataMap["collegeAttended"] = it
            cvGeneratorViewModel.collegeEnrollmentFromDateState.value?.let {
                dataMap["collegeEnrollmentFromDate"] = it
            }
            cvGeneratorViewModel.collegeEnrollmentToDateState.value?.let {
                dataMap["collegeEnrollmentToDate"] = it
            }
            cvGeneratorViewModel.selectedHscBackground.value?.let {
                dataMap["selectedHscBackground"] = it
            }
            cvGeneratorViewModel.hscResultState.value?.let { dataMap["hscResult"] = it }
            cvGeneratorViewModel.universityAttendedState.value?.let {
                dataMap["universityAttended"] = it
            }
            cvGeneratorViewModel.universityEnrollmentFromDateState.value?.let {
                dataMap["universityEnrollmentFromDate"] = it
            }
            cvGeneratorViewModel.universityEnrollmentToDateState.value?.let {
                dataMap["universityEnrollmentToDate"] = it
            }
            cvGeneratorViewModel.selectedUndergradBackground.value?.let {
                dataMap["selectedUndergradBackground"] = it
            }
            cvGeneratorViewModel.universityResultState.value?.let {
                dataMap["universityResult"] = it
            }
            cvGeneratorViewModel.postgraduationAttendedState.value?.let {
                dataMap["postgraduationAttended"] = it
            }
            cvGeneratorViewModel.postGraduationEnrollmentFromDateState.value?.let {
                dataMap["postgraduationEnrollmentFromDate"] = it
            }
            cvGeneratorViewModel.postGraduationEnrollmentToDateState.value?.let {
                dataMap["postgraduationEnrollmentToDate"] = it
            }
            cvGeneratorViewModel.selectedPostgradBackground.value?.let {
                dataMap["selectedPostgradBackground"] = it
            }
            cvGeneratorViewModel.postgraduationResultState.value?.let {
                dataMap["postgraduationResult"] = it
            }
            cvGeneratorViewModel.selectedChooseSkillList.value?.let {
                dataMap["selectedChooseSkillList"] = it
            }
            cvGeneratorViewModel.skillExpertise.value?.let { dataMap["skillExpertise"] = it }
            cvGeneratorViewModel.selectedCoCurrcularActivitiesList.value?.let {
                dataMap["selectedCoCurrcularActivitiesList"] = it
            }
            cvGeneratorViewModel.selectedAddReference.value?.let {
                dataMap["selectedAddReference"] = it
            }
            if (isAddReference) {
                val referenceMapList = mutableListOf<Map<String, Any?>>()
                for (referenceModel in cvGeneratorViewModel.referenceListLiveData.value!!) {
                    val referenceMap = mutableMapOf<String, Any?>()
                    referenceModel.id.let { referenceMap["ref_id"] = it }
                    referenceModel.fullName.let { referenceMap["ref_full_name"] = it }
                    referenceModel.designation.let { referenceMap["ref_designation"] = it }
                    referenceModel.companyName.let { referenceMap["ref_company_name"] = it }
                    referenceModel.email.let { referenceMap["ref_email"] = it }
                    referenceMapList.add(referenceMap)
                }
                referenceMapList.let {
                    dataMap["reference"] = referenceMapList
                }
                cvGeneratorViewModel.currentSelctedtemplates.value?.let {
                    dataMap["selectedTemplate"] = it
                }
                cvGeneratorViewModel.selectedImageUri.value?.let {
                    dataMap["selectedImageUri"] = it
                }

                for ((key, value) in dataMap) {
                    Log.d("SubmitData", "$key: $value")
                }

            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    val navController = rememberNavController()
    val cvGeneratorViewModel: CvGeneratorViewModel = viewModel()
    StarTrekAITheme {
        CVGeneratorScreen(
            navController = navController,
            cvGeneratorViewModel = cvGeneratorViewModel
        )
    }
}
