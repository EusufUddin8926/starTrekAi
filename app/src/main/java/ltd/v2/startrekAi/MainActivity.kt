package ltd.v2.startrekAi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ltd.v2.starTrekAi.util.ErrorCallBackHandler
import ltd.v2.starTrekAi.util.TokenCallBackHandler
import ltd.v2.startrekAi.ui.theme.StarTrekAITheme
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ErrorCallBackHandler.errorCallback = { data ->
            Toast.makeText(this, "Received data: $data", Toast.LENGTH_SHORT).show()
            TokenCallBackHandler.callbackToken?.invoke("new Token pass")
        }

        enableEdgeToEdge()
        setContent {

            var token by rememberSaveable { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                getAuthToken { fetchedToken ->
                    token = fetchedToken

                }
            }

            if (token != null) {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(token!!)
                }
            } else {
                // Optionally, show a loading indicator or some placeholder UI
                LoadingIndicator()
            }


        }

        // Fetch the auth token

    }

    private fun getAuthToken(onTokenReceived: (String) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.151.250.7/")  // Use HTTP instead of HTTPS
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val tokenRequest = mapOf(
            "username" to "FrostyPhoenix842",
            "password" to "G$7kL8zM!wQ2",
            "contact_no" to "01946094342",
            "subscription_plan" to "free",
            "subscription_start_time" to "2024-07-25 10:00:00",
            "subscription_duration" to "30",
            "subscription_status" to "active"
        )

        // Make the API call in a coroutine
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getToken(tokenRequest)
                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    onTokenReceived(tokenResponse?.data?.token ?: "")
                    // Handle the token response
                    println("TokenError: ${tokenResponse?.data?.token}")
                } else {
                    println("TokenError: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()

                println("TokenError: ${e.message}" +" try")
            }
        }
    }
}

interface ApiService {
    @POST("get-token")
    suspend fun getToken(@Body tokenRequest: Map<String, String>): Response<TokenResponse>
}

data class TokenResponse(
    val message: String,
    val data: TokenData
)

data class TokenData(
    val token: String
)

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.scrim)
            .wrapContentSize(Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarTrekAITheme {
        // Your composable preview content
    }
}