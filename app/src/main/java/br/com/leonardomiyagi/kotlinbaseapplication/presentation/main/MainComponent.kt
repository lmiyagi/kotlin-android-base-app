package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.graph.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by lmiyagi on 11/8/17.
 */
@ActivityScoped
@Subcomponent(modules = arrayOf(MainComponent.MainModule::class))
interface MainComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

    @Module
    abstract class MainModule {

        @Binds
        @ActivityScoped
        abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter
    }
}