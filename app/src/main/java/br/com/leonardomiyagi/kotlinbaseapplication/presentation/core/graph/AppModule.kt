package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.utils.DefaultNavigator
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.utils.Navigator
import dagger.Binds
import dagger.Module

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindNavigator(navigator: DefaultNavigator): Navigator
}