
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ltd.v2.starTrekAi.model.ExperienceModel
import ltd.v2.starTrekAi.ui.theme.RoundedShape
import ltd.v2.starTrekAi.viewModel.CvGeneratorViewModel

@Composable
fun ExperienceItem(
    cvGeneratorViewModel: CvGeneratorViewModel,
    experienceModel: ExperienceModel,
    onAddExperienceClick: () -> Unit,
    onRemoveExperienceClick: () -> Unit
) {
    val companyNameState = rememberSaveable { mutableStateOf(experienceModel.companyName) }
    val fromDateState = rememberSaveable { mutableStateOf(experienceModel.fromDate) }
    val toDateState = rememberSaveable { mutableStateOf(experienceModel.toDate) }
    val jobTitleState = rememberSaveable { mutableStateOf(experienceModel.jobTitle) }

    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),

        shape = RoundedShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { onRemoveExperienceClick() },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        tint = Color.White,
                        contentDescription = "Close"
                    )
                }
            }

            OutlinedTextField(
                value = companyNameState.value,
                onValueChange = {
                    companyNameState.value = it
                    experienceModel.companyName = it
                    // cvGeneratorViewModel.setCompanyName(it)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                label = { Text("Company Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )

            OutlinedTextField(
                value = jobTitleState.value,
                onValueChange = {
                    jobTitleState.value = it
                    experienceModel.jobTitle = it
                    // cvGeneratorViewModel.setJobTitle(it)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                label = { Text("Job Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )

            /*OutlinedTextField(
                value = durationState.value,
                onValueChange = {
                    durationState.value = it
                    experienceModel.duration = it
                  //  cvGeneratorViewModel.setDuration(it)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                label = { Text("Duration") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
            )*/

            Box (modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)){
                DatePickerField(
                    context = LocalContext.current,
                    fromDateState = fromDateState.value,
                    toDateState = toDateState.value,
                    onFromDateSelected = { date ->
                        fromDateState.value = date
                        experienceModel.fromDate = date
                    },
                    onToDateSelected = { date ->
                        toDateState.value = date
                        experienceModel.toDate = date
                    }
                )
            }

            if (experienceModel.isExperienceBtnClicked) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                        .height(44.dp),
                    onClick = {
                        experienceModel.isExperienceBtnClicked =
                            false // Hide the button once clicked
                        onAddExperienceClick()
                    },
                    shape = RoundedCornerShape(12),
                    /*colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )*/
                ) {
                    Text(text = "Add More Experience", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}