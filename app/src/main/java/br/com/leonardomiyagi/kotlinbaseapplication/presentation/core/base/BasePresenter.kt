package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.Navigator
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.InteractorHelper
import javax.inject.Inject

abstract class BasePresenter {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var interactorHelper: InteractorHelper
}