package ltd.v2.starTrekAi.api

import ltd.v2.starTrekAi.model.GrammarResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService_Ai {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("grammar-checker")
    suspend fun checkGrammar(
        @Body requestMap: HashMap<String, Any?>
    ): Response<GrammarResponse>


    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("validate-token")
    suspend fun validateToken(
        @Header("Authorization") token: String
    ): Response<Map<String, Any>>
}