package ltd.v2.starTrekAi.view.components

import ReferenceItem
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
import ltd.v2.starTrekAi.model.ReferenceModel

@Composable
fun ShowDynamicReferences(
    referenceList: LiveData<MutableList<ReferenceModel>>,
    onAddReferenceClick: (ReferenceModel) -> Unit,
    onRemoveReferenceClick: (ReferenceModel) -> Unit
) {
    val references by referenceList.observeAsState(initial = mutableListOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 1000.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        items(items = references) { reference ->
            ReferenceItem(
                referenceModel = reference,
                onAddReferenceClick = {
                    onAddReferenceClick(reference)
                },
                onRemoveReferenceClick = {
                    onRemoveReferenceClick(reference)
                }
            )
        }
    }
}
