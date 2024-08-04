package ltd.v2.startrekAi

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ltd.v2.starTrekAi.view.MainActivity_Ai

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(token: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),

                title = { Text("Home") }
            )
        }

    ) { innerPadding ->
        AiOptions(token, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AiOptions(token: String, modifier: Modifier = Modifier) {
    val aiOptions = getAiOptions()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(aiOptions) { index, item ->
            ListOption(token, item)
        }
    }

}

@Composable
fun ListOption(token: String, item: AiOptionsModel) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                when (item.id) {
                    1 -> context.startActivity(Intent(context, MainActivity_Ai::class.java).putExtra("rout", "grammarCheck").putExtra("token", token))
                    2 -> context.startActivity(Intent(context, MainActivity_Ai::class.java).putExtra("rout", "grammarCheck").putExtra("token", token))
                    3 -> context.startActivity(Intent(context, MainActivity_Ai::class.java).putExtra("rout", "cvGenerator").putExtra("token", token))
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = item.imagePath),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            )
            Text(
                text = item.menuTitle,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    StarTrekAITheme {
        HomeScreen()
    }
}*/
