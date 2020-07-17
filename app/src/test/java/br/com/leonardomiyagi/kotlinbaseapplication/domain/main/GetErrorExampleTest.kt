package br.com.leonardomiyagi.kotlinbaseapplication.domain.main

import br.com.leonardomiyagi.kotlinbaseapplication.domain.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class GetErrorExampleTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var getErrorExample: GetErrorExample

    @Before
    fun setUp() {
        getErrorExample = GetErrorExample(repository)
    }

    @Test
    fun `get the expected error from the repository`() {
        runBlocking {
            val errorMessage = "This error is intended"
            val error = Throwable(errorMessage)
            Mockito.`when`(repository.getErrorExample()).thenThrow(error)

            val errorAction = assertFails { repository.getErrorExample() }
            assertNotNull(errorAction)
            assertNotNull(errorAction.message)
            assertEquals(error, errorAction)
            assertEquals(errorAction.message, errorMessage)
        }
    }
}