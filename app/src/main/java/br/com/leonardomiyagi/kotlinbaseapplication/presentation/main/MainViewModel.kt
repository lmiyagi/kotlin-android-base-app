package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.RequestViewModel
import kotlinx.coroutines.launch

/**
 * Created by lmiyagi on 11/8/17.
 */
class MainViewModel constructor(private val getMainMessage: GetMainMessage) : RequestViewModel() {

    val messageLiveData: LiveData<String> get() = _messageLiveData

    private val _messageLiveData = MutableLiveData<String>()

    init {
        getMainMessage()
    }

    private fun getMainMessage() {
        toggleLoading(true)
        viewModelScope.launch {
            runCatching {
                getMainMessage.execute()
            }.onSuccess { message ->
                toggleLoading(false)
                _messageLiveData.postValue(message)
            }.onFailure(::handleError)
        }
    }
}