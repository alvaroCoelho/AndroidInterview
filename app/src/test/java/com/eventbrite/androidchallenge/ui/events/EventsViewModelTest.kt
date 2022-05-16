package com.eventbrite.androidchallenge.ui.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eventbrite.androidchallenge.data.events.makeEventsService
import com.eventbrite.androidchallenge.data.events.model.EventDto
import com.eventbrite.androidchallenge.data.events.model.EventsDto
import com.eventbrite.androidchallenge.repository.EventsRepository
import com.eventbrite.androidchallenge.ui.state.ResourceState
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import okhttp3.OkHttpClient
import okhttp3.Response
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class EventsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()



    @Mock
    private val eventsRepository = EventsRepository(makeEventsService(),Dispatchers.Main)



private  val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()

    }

    @Test
    fun template() = runTest(dispatcher) {
        val viewModel = EventsViewModel(eventsRepository)

        val emptyList = EventsDto(emptyList())

        Mockito.`when`(eventsRepository.listOrganizerEvents())
            .thenReturn(retrofit2.Response.success(emptyList))

             launch(Dispatchers.Main) { viewModel.fetch() }

      assertEquals(ResourceState.Sucess((emptyList)), viewModel.listEvents.value)

    }
}
