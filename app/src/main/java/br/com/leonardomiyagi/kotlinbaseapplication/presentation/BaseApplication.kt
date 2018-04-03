package br.com.leonardomiyagi.kotlinbaseapplication.presentation

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by lmiyagi on 11/8/17.
 */
class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }
}