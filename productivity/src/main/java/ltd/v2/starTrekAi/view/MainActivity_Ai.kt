package ltd.v2.starTrekAi.view

import UIState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ltd.v2.starTrekAi.storage.AppSessionManager
import ltd.v2.starTrekAi.ui.theme.StarTrekAITheme
import ltd.v2.starTrekAi.util.ErrorCallBackHandler
import ltd.v2.starTrekAi.util.Routs
import ltd.v2.starTrekAi.view.screen.CVGeneratorScreen
import ltd.v2.starTrekAi.view.screen.GrammarCheckScreen
import ltd.v2.starTrekAi.viewModel.CvGeneratorViewModel
import ltd.v2.starTrekAi.viewModel.GrammarCheckViewModel
import ltd.v2.starTrekAi.viewModel.HomeScreenViewModel

class MainActivity_Ai : ComponentActivity() {
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val grammarCheckViewModel: GrammarCheckViewModel by viewModels()
    private val cvGeneratorViewModel: CvGeneratorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            StarTrekAITheme {
                if (intent == null) {
                    //HomeScreen(navController = rememberNavController())
                    return@StarTrekAITheme
                }
                val route = intent.getStringExtra("rout")
                val token = intent.getStringExtra("token")


                var tokenData by rememberSaveable { mutableStateOf<Map<String, Any>?>(null) }

                ValidateToken(homeScreenViewModel, token) { data ->
                    tokenData = data
                    AppSessionManager.setToken(token!!)
                }

                if (tokenData != null && route != null && Routs.listOfRoute.contains(route)) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = route.toString()) {
                        composable(Routs.home) {
                            //HomeScreen(navController = navController)
                        }
                        composable(Routs.grammarCheck) {
                            GrammarCheckScreen(
                                navController = navController,
                                viewModel = grammarCheckViewModel,
                                tokenData = tokenData!!
                            )
                        }
                        composable(Routs.cvGenerator) {
                            CVGeneratorScreen(navController = navController, cvGeneratorViewModel)
                        }
                    }
                } else {
                    //HomeScreen(navController = rememberNavController())
                }

            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
                val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                view.updatePadding(bottom = bottom)
                insets
            }
        }

    }
}


@Composable
fun ValidateToken(
    homeScreenViewModel: HomeScreenViewModel, token: String?,
    onSuccess: (Map<String, Any>) -> Unit
) {
    val context = LocalContext.current as ComponentActivity
    var isLoading by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        homeScreenViewModel.validateToken("Bearer " + token!!)
        onDispose { homeScreenViewModel.cancelJobs() }
    }

    LaunchedEffect(token) {
        homeScreenViewModel.tokenDataState.collect { state ->
            when (state) {
                is UIState.Loading -> {
                    isLoading = true
                }
                is UIState.Success -> {
                    onSuccess(state.data)
                    isLoading = false
                }
                is UIState.Error -> {
                    isLoading = false
                    ErrorCallBackHandler.errorCallback?.invoke(state.exception.message.toString())
                    context.finish()
                }
                else -> {

                }
            }
        }
    }

    if (isLoading) {
        LoadingIndicator()
    }

}

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.scrim)
            .wrapContentSize(Alignment.Center)
    )
}




