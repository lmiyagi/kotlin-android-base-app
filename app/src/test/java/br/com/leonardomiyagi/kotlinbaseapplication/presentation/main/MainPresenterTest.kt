package br.com.leonardomiyagi.kotlinbaseapplication.presentation.main

import br.com.leonardomiyagi.kotlinbaseapplication.core.BasePresenterTest
import br.com.leonardomiyagi.kotlinbaseapplication.domain.main.GetMainMessage
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 09/04/18.
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest : BasePresenterTest<MainContract.View, MainPresenter>() {

    @Mock
    private lateinit var getMainMessage: GetMainMessage

    @Before
    fun setUp() {
        super.setupPresenter(MainPresenter(getMainMessage))
    }

    @Test
    fun testAttachView_success() {
        val expectedMessage = "This message is expected to be sent by the Repository"
        whenever(interactorHelper.execute(getMainMessage)).thenReturn(Single.just(expectedMessage))
        presenter.attachView(view)

        verify(view).renderMessage(expectedMessage)
    }

    @Test
    fun testAttachView_error() {
        val captor = argumentCaptor<() -> Unit>()

        val errorMessage = "Error Message"
        val error = Throwable(errorMessage)
        whenever(interactorHelper.execute(getMainMessage)).thenReturn(Single.error(error))
        presenter.attachView(view)

        verify(view).showError(eq(error), captor.capture())
    }
}