package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class RequestViewModel : BaseViewModel() {
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData
    private val _loadingLiveData = MutableLiveData<Boolean>()

    fun toggleLoading(isLoading: Boolean) {
        _loadingLiveData.postValue(isLoading)
    }
}