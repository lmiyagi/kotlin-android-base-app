package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.data.repository.DefaultRepository
import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.providers.DefaultSchedulerProvider
import dagger.Binds
import dagger.Module

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindSchedulerProvider(schedulerProvider: DefaultSchedulerProvider): SchedulerProvider

    @Binds
    abstract fun bindRepository(repository: DefaultRepository): Repository
}