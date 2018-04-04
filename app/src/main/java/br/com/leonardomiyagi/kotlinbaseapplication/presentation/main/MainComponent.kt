package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by lmiyagi on 11/8/17.
 */
@ActivityScoped
@Subcomponent(modules = [(MainComponent.MainModule::class)])
interface MainComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

    @Module
    abstract class MainModule {

        @Binds
        abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter

        @Module
        companion object {

            @Provides
            fun provideGetMainMessage(repository: Repository): GetMainMessage {
                return GetMainMessage(repository)
            }
        }
    }
}