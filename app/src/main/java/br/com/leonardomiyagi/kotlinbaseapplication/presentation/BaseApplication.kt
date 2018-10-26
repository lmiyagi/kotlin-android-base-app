package br.com.leonardomiyagi.kotlinbaseapplication.presentation

import android.app.Application
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph.apiModule
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph.appModule
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.mainModule
import org.koin.android.ext.android.startKoin

/**
 * Created by lmiyagi on 11/8/17.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, apiModule, mainModule))
    }
}