package com.eventbrite.androidchallenge.repository

import com.eventbrite.androidchallenge.data.events.EventsService
import com.eventbrite.androidchallenge.data.events.model.EventsDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class EventsRepository constructor(private val eventsService: EventsService,
                                   private val defaultDisparcher: CoroutineDispatcher
) {


    suspend fun listOrganizerEvents():Response<EventsDto> {
        withContext(defaultDisparcher) {}
      return eventsService.listOrganizerEvents()
    }
}

