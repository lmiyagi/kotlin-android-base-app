package br.com.leonardomiyagi.kotlinbaseapplication.core

import br.com.leonardomiyagi.kotlinbaseapplication.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BaseContract
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.core.base.BasePresenter
import br.com.leonardomiyagi.kotlinbaseapplication.presentation.utils.InteractorHelper
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.Schedulers
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 09/04/18.
 */
@RunWith(MockitoJUnitRunner::class)
open class BasePresenterTest<View : BaseContract.View, Presenter : BasePresenter<View>> {

    @Mock
    private lateinit var schedulerProvider: SchedulerProvider
    @Mock
    protected lateinit var interactorHelper: InteractorHelper
    @Mock
    protected lateinit var view: View

    protected lateinit var presenter: Presenter

    protected fun setupPresenter(presenter: Presenter) {
        this.presenter = presenter
        // todo fix view issues
        val viewField = presenter.javaClass.superclass.getDeclaredField("view")
        viewField.isAccessible = true
        viewField.set(presenter, view)

        val schedulperProviderField = InteractorHelper::class.java.getDeclaredField("schedulers")
        schedulperProviderField.isAccessible = true
        schedulperProviderField.set(interactorHelper, schedulerProvider)

        presenter.interactorHelper = interactorHelper
        whenever(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.main()).thenReturn(Schedulers.trampoline())
    }
}