
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ltd.v2.starTrekAi.ui.theme.RoundedShape

@Composable
fun ImagePickerCompose(selectedImageUri: Uri?,onImageSelected: (Uri) -> Unit) {

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            onImageSelected(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedShape)
    ) {
        if (selectedImageUri == null) {
            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "Select Image from Gallery",  color =  MaterialTheme.colorScheme.onSurface)
            }
        } else {
            Image(
                painter = rememberImagePainter(data = selectedImageUri),
                contentDescription = null,
                modifier = Modifier.fillMaxSize() .clip(RoundedShape) .clickable {
                    launcher.launch("image/*")
                },
                contentScale = ContentScale.Crop
            )
        }
    }
}
