package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.ACTIVITY_SCOPE
import org.koin.dsl.module.module

/**
 * Created by lmiyagi on 11/8/17.
 */
val mainModule = module {

    scope<MainContract.Presenter>(ACTIVITY_SCOPE) { MainPresenter(get()) }
    scope(ACTIVITY_SCOPE) { GetMainMessage(get()) }
}