package br.com.leonardomiyagi.kotlinbaseapplication.core

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

/**
 * Created by lmiyagi on 31/10/18.
 */
val appModule = module {
    single<SchedulerProvider> {
        object : SchedulerProvider {
            override fun io(): Scheduler {
                return Schedulers.trampoline()
            }

            override fun main(): Scheduler {
                return Schedulers.trampoline()
            }
        }
    }
}