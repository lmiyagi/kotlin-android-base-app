package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 10/04/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetMainMessageTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var getMainMessage: GetMainMessage

    @Before
    fun setUp() {
        getMainMessage = GetMainMessage(repository)
    }

    @Test
    fun interactorTest_success() {
        val expectedResult = "This is the expected result."
        `when`(repository.getMessage()).thenReturn(Single.just(expectedResult))

        val testObserver = getMainMessage.execute().test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValue(expectedResult)
    }

    @Test
    fun interactorTest_error() {
        val errorMessage = "This error is intended"
        val error = Throwable(errorMessage)
        `when`(repository.getMessage()).thenReturn(Single.error(error))

        val testObserver = getMainMessage.execute().test()
        testObserver.assertNoValues()
        testObserver.assertError(error)
        testObserver.assertErrorMessage(errorMessage)
    }
}