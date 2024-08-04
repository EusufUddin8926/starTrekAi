package ltd.v2.starTrekAi.view.screen

import UIState
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import es.dmoral.toasty.Toasty
import ltd.v2.starTrekAi.R
import ltd.v2.starTrekAi.storage.AppSessionManager
import ltd.v2.starTrekAi.ui.theme.RoundedButtonShape
import ltd.v2.starTrekAi.ui.theme.RoundedShape
import ltd.v2.starTrekAi.ui.theme.StarTrekAITheme
import ltd.v2.starTrekAi.util.ErrorCallBackHandler
import ltd.v2.starTrekAi.util.TokenCallBackHandler
import ltd.v2.starTrekAi.util.countWords
import ltd.v2.starTrekAi.util.isInternetAvailable
import ltd.v2.starTrekAi.viewModel.GrammarCheckViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarCheckScreen(
    navController: NavHostController,
    viewModel: GrammarCheckViewModel,
    tokenData: Map<String, Any>
) {

    val textToCheck by viewModel.textToCheck.observeAsState(initial = "")
    val resultText by viewModel.grammarCheckResult.observeAsState(initial = "")
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val clipboardManager: androidx.compose.ui.platform.ClipboardManager =
        LocalClipboardManager.current
    var isLoading by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    TokenCallBackHandler.callbackToken= { token ->
        AppSessionManager.setToken(token)
        tokenData.plus("data" to token)
        CheckTheGrammar(viewModel, lifecycleOwner, tokenData, context){
            isLoading = it
        }
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
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },

                title = {
                    Text(
                        stringResource(R.string.title_activity_grammer_check),
                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )
                },

                actions = {
                    IconButton(
                        onClick = {

                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_history),
                            contentDescription = "Favorite",
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.setTextToCheck("")
                            viewModel.setGrammarCheckResult("")
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_refresh),
                            contentDescription = "Favorite",
                        )
                    }
                },


                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        )
        {

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Input",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            //Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedShape
                    )
            ) {
                Column {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(8f)
                    ) {
                        TextField(
                            placeholder = { Text("To get started, please write some text OR paste a text") },
                            value = textToCheck,
                            singleLine = false,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                autoCorrect = false,
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            ),
                            onValueChange = {
                                viewModel.setTextToCheck(it)
                            },
                            modifier = Modifier
                                .fillMaxSize()

                        )
                        if (textToCheck.isEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 80.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    "Click here to  ",
                                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier
                                        .padding(6.dp)


                                )
                                Box(modifier = Modifier
                                    .border(
                                        BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                        RoundedShape
                                    )
                                    .padding(6.dp)
                                    .clickable {
                                        val clipboardText = clipboardManager.getText()?.text
                                        if (clipboardText != null) {
                                            viewModel.setTextToCheck(clipboardText)
                                        }
                                    }) {
                                    Text(
                                        "Paste from clipboard",
                                        fontSize = 16.sp, fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,

                                        )
                                }
                            }
                        }

                    }

                    if (textToCheck.isNotEmpty() ) {
                        Button(
                            onClick = {
                                keyboardController?.hide()
                                if(!isLoading){
                                    CheckTheGrammar(viewModel, lifecycleOwner, tokenData, context){
                                        isLoading = it
                                    }
                                }

                               },
                            shape = RoundedButtonShape,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                            //.weight(1f)
                        ) {

                            if(isLoading){
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    strokeWidth = 2.dp,
                                    strokeCap = StrokeCap.Round,
                                    modifier = Modifier.size(20.dp)
                                )
                            }else{
                                Text(
                                    text = "Check Grammar",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {

                if (textToCheck.isNotEmpty()) {

                    Text(
                        text = "${countWords(textToCheck)} Words",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Output",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedShape
                    )
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Trailing icon

                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            placeholder = { Text("Output will appear here") },
                            value = resultText,
                            singleLine = false,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                autoCorrect = false,
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            ),
                            onValueChange = {
                                viewModel.setGrammarCheckResult(it)
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp)

                        )


                        if (resultText.isNotEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_copy),
                                contentDescription = "Grammar Check",
                                modifier = Modifier
                                    .align(Alignment.TopEnd) // Align to top-end (right)
                                    .padding(
                                        end = 8.dp,
                                        top = 8.dp
                                    ) // Adjust padding to place icon properly
                                    .clickable {
                                        clipboardManager.setText(AnnotatedString(resultText))
                                        Toasty
                                            .success(
                                                context,
                                                "Copied to clipboard",
                                                Toasty.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            )
                        }


                    }



                }

            }

            Spacer(modifier = Modifier.height(32.dp))
        }

    }
}



fun CheckTheGrammar(
    viewModel: GrammarCheckViewModel,
    current: LifecycleOwner,
    tokenData: Map<String, Any>,
    context: Context,
    setLoadingState: (Boolean) -> Unit
) {

    val requestMap = hashMapOf(
        "content" to viewModel.textToCheck.value.toString(),
        "token_data" to tokenData["data"]
    )

    if(!isInternetAvailable(context = context)){
        Toasty.warning(context, context.getString(R.string.no_internet_available_message), Toasty.LENGTH_LONG).show()
        return
    }


    viewModel.checkGrammar(requestMap)
    viewModel.grammarDataState.observe(current) { state ->
        when (state) {
            is UIState.Loading -> {
                setLoadingState(true)
            }
            is UIState.Success -> {
                setLoadingState(false)
                viewModel.setGrammarCheckResult(state.data.data?.result.toString())
                ErrorCallBackHandler.errorCallback?.invoke("Error message will be here")
            }
            is UIState.Error -> {
                Toasty.warning(context, ""+state.exception.message.toString(), Toasty.LENGTH_SHORT).show()
                //ErrorCallBackHandler.errorCallback?.invoke(state.exception.message.toString())
                setLoadingState(false)
            }
            else -> {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val navController = rememberNavController()
    StarTrekAITheme {
        GrammarCheckScreen(
            navController = navController,
            viewModel = GrammarCheckViewModel(),
            tokenData = mapOf("data" to "data")
        )
    }
}

