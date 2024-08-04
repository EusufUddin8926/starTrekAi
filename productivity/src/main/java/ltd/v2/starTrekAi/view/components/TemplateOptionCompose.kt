
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ltd.v2.starTrekAi.model.TemplateModel

@Composable
fun TemplateGrid(templates: List<TemplateModel>, onSelect: (TemplateModel) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().heightIn(max = 400.dp)
    ) {
        items(templates) { template ->
            TemplateItem(template = template, onSelect = onSelect)
        }
    }
}
