package com.eventbrite.androidchallenge.ui.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eventbrite.androidchallenge.data.events.makeEventsService
import com.eventbrite.androidchallenge.repository.EventsRepository
import com.eventbrite.androidchallenge.ui.state.ResourceState
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class EventsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

private  val dispatcher = UnconfinedTestDispatcher()

    @Mock
    private var eventsRepository = EventsRepository(makeEventsService())

  /*  @InjectMocks
    val viewModel = EventsViewModel(eventsRepository,Dispatchers.Default)
*/

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

        val viewModel = EventsViewModel(eventsRepository,Dispatchers.Main)

       launch(Dispatchers.Main) { viewModel.fetch() }

        assertEquals(viewModel.listEvents.value, ResourceState.Sucess(Any()))

    }
}
