package br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.graph

import br.com.leonardomiyagi.kotlinbaseapplication.data.repository.DefaultRepository
import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.providers.DefaultSchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by lmiyagi on 11/8/17.
 */
val appModule = module {
    single<SchedulerProvider> { DefaultSchedulerProvider() }
    single<Repository> { DefaultRepository(get()) }
}