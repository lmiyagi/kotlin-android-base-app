package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by lmiyagi on 11/8/17.
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (AppModule::class),
    (BindingModule::class),
    (ApiModule::class)])
interface AppComponent : AndroidInjector<BaseApplication>