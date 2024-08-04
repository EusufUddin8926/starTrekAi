

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ltd.v2.starTrekAi.ui.theme.RoundedShape

@Composable
fun DynamicChipGroupCompose(
    chipList: List<String>,
    selectedChip: String?,
    onChipSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        userScrollEnabled = false,
        columns = GridCells.Fixed(2), // 2 columns
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 1000.dp)
    ) {
        items(chipList) { chipTitle ->
            Chip(
                title = chipTitle,
                isSelected = chipTitle == selectedChip,
                onSelected = { onChipSelected(chipTitle) },
                modifier = Modifier.padding(4.dp) // Add padding to separate the items
            )
        }
    }
}

@Composable
fun Chip(
    title: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val contentColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = modifier
            .background(background, shape = RoundedShape)
            .clickable(onClick = onSelected)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical =12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                color = contentColor,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
