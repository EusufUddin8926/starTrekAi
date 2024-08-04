import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {

    private lateinit var tokenProvider: () -> String

    fun setTokenProvider(provider: () -> String) {
        tokenProvider = provider
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!::tokenProvider.isInitialized) {
            throw IllegalStateException("Token provider must be set before using AuthInterceptor")
        }
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${tokenProvider()}")
            .build()
        return chain.proceed(newRequest)
    }
}
