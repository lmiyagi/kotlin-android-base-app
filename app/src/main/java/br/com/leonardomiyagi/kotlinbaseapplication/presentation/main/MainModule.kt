package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by lmiyagi on 11/8/17.
 */
val mainModule = module {

    scope(named<MainActivity>()) {
        scoped<MainContract.Presenter> { MainPresenter(get()) }
        scoped { GetMainMessage(get()) }
    }
}