package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull

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
    fun `get the expected result from the repository`() {
        runBlocking {
            val expectedResult = "This is the expected result."
            `when`(repository.getMessage()).thenReturn(expectedResult)

            val message = repository.getMessage()
            assertNotNull(message)
            assertEquals(message, expectedResult)
        }
    }

    @Test
    fun `get the expected error from the repository`() {
        runBlocking {
            val errorMessage = "This error is intended"
            val error = Throwable(errorMessage)
            `when`(repository.getMessage()).thenThrow(error)

            val errorAction = assertFails { repository.getMessage() }
            assertNotNull(errorAction)
            assertNotNull(errorAction.message)
            assertEquals(error, errorAction)
            assertEquals(errorAction.message, errorMessage)
        }
    }
}