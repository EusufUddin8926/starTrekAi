
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import ltd.v2.starTrekAi.model.ReferenceModel
import ltd.v2.starTrekAi.ui.theme.RoundedShape

@Composable
fun ReferenceItem(
    referenceModel: ReferenceModel,
    onAddReferenceClick: () -> Unit,
    onRemoveReferenceClick: () -> Unit
) {

    /*val referralFullNameText by cvGeneratorViewModel.referralFullName.observeAsState(initial = "")
    val referralDesignationText by cvGeneratorViewModel.referralDesignation.observeAsState(initial = "")
    val referralCompanyNameText by cvGeneratorViewModel.referralCompanyName.observeAsState(initial = "")*/
    val referralFullNameText = rememberSaveable { mutableStateOf(referenceModel.fullName) }
    val referralDesignationText = rememberSaveable { mutableStateOf(referenceModel.designation) }
    val referralCompanyNameText = rememberSaveable { mutableStateOf(referenceModel.companyName) }
    val referralEmailText = rememberSaveable { mutableStateOf(referenceModel.email) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
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
                    onClick = { onRemoveReferenceClick() },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        tint = Color.White,
                        contentDescription = "Close"
                    )
                }
            }

            OutlinedTextField(
                value = referralFullNameText.value,

                onValueChange = {
                    referralFullNameText.value = it
                    referenceModel.companyName = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                label = { Text("Full Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)

            )
            OutlinedTextField(
                value = referralDesignationText.value,
                onValueChange = {
                    // cvGeneratorViewModel.setReferralDesignation(it)
                    referralDesignationText.value = it
                    referenceModel.designation = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                label = { Text("Designation") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )
            OutlinedTextField(
                value = referralCompanyNameText.value,
                onValueChange = {
                    //   cvGeneratorViewModel.setReferralCompanyName(it)
                    referralCompanyNameText.value = it
                    referenceModel.companyName = it
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
                value = referralEmailText.value,
                onValueChange = {
                    //   cvGeneratorViewModel.setReferralCompanyName(it)
                    referralEmailText.value = it
                    referenceModel.email = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
            )

            if (referenceModel.isRefereceBtnClicked) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                        .height(44.dp),
                    onClick = {
                        referenceModel.isRefereceBtnClicked = false // Hide the button once clicked
                        onAddReferenceClick()
                    },
                    shape = RoundedCornerShape(12),
                    /*colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )*/
                ) {
                    Text(text = "Add More Reference", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}
