package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.Navigator
import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.providers.DefaultNavigator
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.providers.DefaultSchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.InteractorHelper
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindNavigator(navigator: DefaultNavigator): Navigator

    @Binds
    abstract fun bindSchedulerProvider(schedulerProvider: DefaultSchedulerProvider): SchedulerProvider

    @Module
    companion object {

        @Provides
        fun provideInteractorHelper(schedulerProvider: SchedulerProvider): InteractorHelper {
            return InteractorHelper(schedulerProvider)
        }
    }
}