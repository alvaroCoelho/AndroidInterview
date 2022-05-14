package com.eventbrite.androidchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventbrite.androidchallenge.data.events.model.EventDto
import com.eventbrite.androidchallenge.databinding.EventCardBinding
import com.eventbrite.androidchallenge.utils.formatDateToString

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    inner class EventsViewHolder(val binding: EventCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<EventDto>() {
        override fun areItemsTheSame(
            oldItem: EventDto,
            newItem: EventDto
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: EventDto, newItem: EventDto): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.startDate == newItem.startDate
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var events: MutableList<EventDto>
    get() = differ.currentList
    set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            EventCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]
        holder.binding.apply {
            tvtitle.text = event.name
            tvStartDate.text = formatDateToString(event.startDate)

            Glide.with(holder.itemView.context)
                .load(event.image?.url.toString())
                .into(imgEvent)
        }
    }

    override fun getItemCount(): Int = events.size
}
