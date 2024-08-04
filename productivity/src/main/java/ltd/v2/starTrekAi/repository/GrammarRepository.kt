package ltd.v2.starTrekAi.repository

import UIState
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import ltd.v2.starTrekAi.api.NetworkModule_Ai.apiService
import ltd.v2.starTrekAi.model.GrammarResponse
import org.json.JSONObject


object GrammarRepository  {

    private var job: CompletableJob? = null

    suspend fun checkGrammar(requestMap: HashMap<String, Any?>): UIState<GrammarResponse> {
        job = Job()
        return withContext(Dispatchers.IO + job!!) {
            try {
                val response = apiService.checkGrammar(requestMap)
                if (response.isSuccessful) {
                    UIState.Success(response.body()!!)
                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        UIState.Error(Exception(jObjError.getString("message").toString()))
                    } catch (e: Exception) {
                        UIState.Error(Exception("Service is currently unavailable. Please try again shortly."))
                    }
                }
            } catch (e: Exception) {
                UIState.Error(Exception("Service is currently unavailable. Please try again shortly."))
            } finally {
                job!!.complete()
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}