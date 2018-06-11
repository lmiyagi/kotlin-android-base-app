package br.com.leonardomiyagi.kotlinbaseapplication.core

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BaseContract
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BasePresenter
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.Schedulers
import org.mockito.Mock

/**
 * Created by lmiyagi on 09/04/18.
 */
open class BasePresenterTest<View : BaseContract.View, Presenter : BasePresenter<View>> {

    @Mock
    private lateinit var schedulerProvider: SchedulerProvider

    protected lateinit var presenter: Presenter

    protected fun setupPresenter(view: View, presenter: Presenter) {
        this.presenter = presenter

        val viewField = presenter.javaClass.superclass.superclass.getDeclaredField("view")
        viewField.isAccessible = true
        viewField.set(presenter, view)

        val schedulersField = presenter.javaClass.superclass.superclass.getDeclaredField("schedulers")
        schedulersField.isAccessible = true
        schedulersField.set(presenter, schedulerProvider)

        whenever(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.main()).thenReturn(Schedulers.trampoline())
    }
}