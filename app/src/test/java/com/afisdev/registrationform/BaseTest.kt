package com.afisdev.registrationform

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.afisdev.registrationform.domain.MainUseCase
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Created by afisdev on 14/09/2023.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
@SmallTest
abstract class BaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    val testDispatchers = UnconfinedTestDispatcher()

    val mockMainUseCase: MainUseCase = mockk(relaxed = true)

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatchers)
    }


    @After
    open fun cleanUp() {
        Dispatchers.resetMain()
    }

    fun generateRandomString(): String {
        return ('A'..'z').map { it }.shuffled().subList(0, 5).joinToString("")
    }
}