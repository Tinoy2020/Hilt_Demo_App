package com.hiltdemoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hiltdemoapp.data.model.UsersList
import com.hiltdemoapp.data.repository.ApiRepository
import com.hiltdemoapp.ui.main.viewmodel.MainViewModel
import com.hiltdemoapp.utils.NetworkHelper
import com.hiltdemoapp.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<UsersList>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        val viewModel = MainViewModel(apiRepository, networkHelper)
        testCoroutineRule.runBlockingTest {
            doReturn(null)
                .`when`(apiRepository)
                .getUsers("1")
            viewModel.users.observeForever(apiUsersObserver)
            verify(apiRepository).getUsers("1")
            verify(apiUsersObserver).onChanged(Resource.success(data = null))
            viewModel.users.removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        val viewModel = MainViewModel(apiRepository, networkHelper)
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message test"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiRepository)
                .getUsers("1")
            viewModel.users.observeForever(apiUsersObserver)
            verify(apiRepository).getUsers("1")
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    msg = RuntimeException(errorMessage).toString(),
                    data = null
                )
            )
            viewModel.users.removeObserver(apiUsersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}