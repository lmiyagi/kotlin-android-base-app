package br.com.leonardomiyagi.kotlinbaseapplication.core

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BaseContract
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest

/**
 * Created by lmiyagi on 09/04/18.
 */
abstract class BasePresenterTest<View : BaseContract.View, Presenter : BasePresenter<View>> : KoinTest {

    protected lateinit var presenter: Presenter

    @Before
    fun baseSetUp() {
        startKoin(listOf(appModule))
        setUp()
        if (!this::presenter.isInitialized) {
            throw RuntimeException("You must setup the preseter using super.setupPresenter() on your setUp method.")
        }
    }

    @After
    fun baseTearDown() {
        stopKoin()
        tearDown()
    }

    protected abstract fun setUp()

    protected abstract fun tearDown()

    protected fun setupPresenter(view: View, presenter: Presenter) {
        this.presenter = presenter

        val viewField = presenter.javaClass.superclass.superclass.getDeclaredField("view")
        viewField.isAccessible = true
        viewField.set(presenter, view)
    }
}