package com.eventbrite.androidchallenge.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventbrite.androidchallenge.data.events.model.EventsDto
import com.eventbrite.androidchallenge.repository.EventsRepository
import com.eventbrite.androidchallenge.ui.state.ResourceState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import java.io.IOException

class EventsViewModel(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    private val _listEvents = MutableLiveData<ResourceState<EventsDto>>(ResourceState.Loading())

    val listEvents: MutableLiveData<ResourceState<EventsDto>> = _listEvents

    init {
        fetch()
    }

    fun fetch() = viewModelScope.launch {

                try {
                    val response = eventsRepository.listOrganizerEvents()
                    _listEvents.value = handleResponse(response)
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> _listEvents.value = ResourceState.Error("erro de conexão")
                        else -> _listEvents.value = ResourceState.Error("falha nos dados")
                    }
                }

        }
    }

    private fun handleResponse(response: Response<EventsDto>): ResourceState<EventsDto>? {

            if (response.isSuccessful) {
                response.body()?.let { values ->
                    return ResourceState.Sucess(values)
                }
            }
            return ResourceState.Error(response.message())
        }







