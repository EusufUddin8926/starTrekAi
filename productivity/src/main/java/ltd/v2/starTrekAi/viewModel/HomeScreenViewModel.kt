package ltd.v2.starTrekAi.viewModel

import UIState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ltd.v2.starTrekAi.repository.TokenValidateRepository

class HomeScreenViewModel : ViewModel() {

    private val _dataState = MutableStateFlow<UIState<Map<String, Any>>>(UIState.Loading)
    val tokenDataState: StateFlow<UIState<Map<String, Any>>> get() = _dataState

    fun validateToken(token: String) {
        viewModelScope.launch {
            _dataState.value = UIState.Loading
            try {
                val result = TokenValidateRepository.validateToken(token)
                _dataState.value = result
            } catch (e: Exception) {
                _dataState.value = UIState.Error(e)
            }
        }
    }

    fun cancelJobs() {
        TokenValidateRepository.cancelJobs()
    }
}


