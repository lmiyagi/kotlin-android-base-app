package br.com.leonardomiyagi.kotlinbaseapplication.domain.provider

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler
    fun main(): Scheduler
}