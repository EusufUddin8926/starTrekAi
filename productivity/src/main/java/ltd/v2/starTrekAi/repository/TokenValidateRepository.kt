package ltd.v2.starTrekAi.repository

import UIState
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import ltd.v2.starTrekAi.api.NetworkModule_Ai.apiService

object TokenValidateRepository {

    private var job: CompletableJob? = null

    suspend fun validateToken(token: String): UIState<Map<String, Any>> {
        job = Job()
        return withContext(Dispatchers.IO + job!!) {
            try {
                val response = apiService.validateToken(token)
                if (response.isSuccessful) {
                    UIState.Success(response.body()!!)
                } else {
                    UIState.Error(Exception("The Token is expired"))
                }
            } catch (e: Exception) {
                UIState.Error(Exception("The Token is expired"))
            } finally {
                job!!.complete()
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}