package com.eventbrite.androidchallenge.repository

import com.eventbrite.androidchallenge.data.events.EventsService
import com.eventbrite.androidchallenge.data.events.model.EventsDto
import retrofit2.Response

class EventsRepository constructor(private val eventsService: EventsService){

   suspend fun listOrganizerEvents (): Response<EventsDto>{
       return eventsService.listOrganizerEvents()
   }
}

