package br.com.leonardomiyagi.kotlinbaseapplication.presentation.extentions

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@MainThread
fun <T : Any> LiveData<T>.observeDirectly(owner: LifecycleOwner, action: (value: T) -> Unit) {
    observe(owner, Observer { action(it) })
}