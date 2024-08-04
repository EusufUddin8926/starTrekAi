package ltd.v2.starTrekAi.viewModel

import UIState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ltd.v2.starTrekAi.model.GrammarResponse
import ltd.v2.starTrekAi.repository.GrammarRepository

class GrammarCheckViewModel : ViewModel(){

    private val _textToCheck = MutableLiveData<String>()
    val textToCheck : LiveData<String> = _textToCheck

    fun setTextToCheck(text: String){
        _textToCheck.value = text
    }


    private val _grammarCheckResult = MutableLiveData<String>()
    val grammarCheckResult : LiveData<String> = _grammarCheckResult

    fun setGrammarCheckResult(result: String){
        _grammarCheckResult.value = result
    }

    //...........  Grammar Check API call will be here .............
    private val _dataState = MutableLiveData<UIState<GrammarResponse>>()
    val grammarDataState: LiveData<UIState<GrammarResponse>> get() = _dataState


     fun checkGrammar(requestMap: HashMap<String, Any?>) {
        viewModelScope.launch {
            _dataState.value = UIState.Loading
            _dataState.value = GrammarRepository.checkGrammar(requestMap)
        }
    }

    fun cancelJobs() {
        GrammarRepository.cancelJobs()
    }



}