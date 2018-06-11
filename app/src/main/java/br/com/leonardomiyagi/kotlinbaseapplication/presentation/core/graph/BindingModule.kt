package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.MainActivity
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class BindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeLoginActivity(): MainActivity
}