package com.eventbrite.androidchallenge.data.events

import com.eventbrite.androidchallenge.data.events.model.EventsDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EventsService {

    @GET("destination/organizers/22699500963/events/?expand=image&token=QGSCFRJYOKAA7IDDPMON")
    suspend fun listOrganizerEvents(): Response<EventsDto>
}


fun makeEventsService(): EventsService = makeRetrofit().create(EventsService::class.java)

private fun makeRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.evbqaapi.com/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
