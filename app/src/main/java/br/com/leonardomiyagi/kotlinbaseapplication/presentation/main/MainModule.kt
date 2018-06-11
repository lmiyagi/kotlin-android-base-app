package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph.ActivityScoped
import dagger.Binds
import dagger.Module

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class MainModule {

    @Binds
    @ActivityScoped
    abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter
}