package com.eventbrite.androidchallenge.data.events.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URL
import java.util.*

data class EventDto(
    val id: String,
    val name: String,
    @SerializedName("start_date")
    val startDate: Date,
    val image: ImageDto? = null
): Serializable

data class EventsDto(
    val events: List<EventDto>
):Serializable

data class ImageDto(
    val url: URL
)
