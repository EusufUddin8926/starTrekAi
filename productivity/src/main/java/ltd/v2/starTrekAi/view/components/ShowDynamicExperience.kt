package ltd.v2.starTrekAi.view.components

import ExperienceItem
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import ltd.v2.starTrekAi.model.ExperienceModel
import ltd.v2.starTrekAi.viewModel.CvGeneratorViewModel

@Composable
fun ShowDynamicExperience(
    cvGeneratorViewModel: CvGeneratorViewModel,
    experienceList: LiveData<MutableList<ExperienceModel>>,
    onAddExperienceClick: (ExperienceModel) -> Unit,
    onRemoveExperienceClick: (ExperienceModel) -> Unit
) {
    val experiences by experienceList.observeAsState(initial = mutableListOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 1000.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        items(items = experiences) { experience ->
            ExperienceItem(
                cvGeneratorViewModel = cvGeneratorViewModel,
                experienceModel = experience,
                onAddExperienceClick = {
                    onAddExperienceClick(experience)
                },
                onRemoveExperienceClick = {
                    onRemoveExperienceClick(experience)
                }
            )
        }
    }
}
