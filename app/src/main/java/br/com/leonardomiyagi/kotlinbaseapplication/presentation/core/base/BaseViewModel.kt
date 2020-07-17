package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val errorLiveData: LiveData<Throwable> get() = _errorLiveData
    private val _errorLiveData = MutableLiveData<Throwable>()

    protected open fun handleError(exception: Throwable) {
        _errorLiveData.postValue(exception)
    }
}