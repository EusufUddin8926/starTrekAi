package ltd.v2.starTrekAi.api

import AuthInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import ltd.v2.starTrekAi.storage.AppSessionManager
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule_Ai {

    private const val BASE_URL: String = "http://54.151.250.7/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        val okHttpClient = createOkHttpClient()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService_Ai by lazy {
        retrofitBuilder
            .build()
            .create(ApiService_Ai::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val authInterceptor: AuthInterceptor by lazy {
        AuthInterceptor.apply {
            setTokenProvider { AppSessionManager.getToken() }
        }
    }


    private val loggingInterceptor: LoggingInterceptor by lazy {
        LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("API_REQ")
            .response("API_RES")
            .build()
    }


}

