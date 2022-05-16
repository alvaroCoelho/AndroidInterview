package com.eventbrite.androidchallenge.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eventbrite.androidchallenge.data.events.EventsService
import com.eventbrite.androidchallenge.data.events.makeEventsService
import com.eventbrite.androidchallenge.repository.EventsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class EventsViewModelFactory
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            return EventsViewModel(EventsRepository(makeEventsService(),Dispatchers.Main)) as T
        } else {
            throw IllegalArgumentException("ViewModel $modelClass not supported")
        }
    }
}
