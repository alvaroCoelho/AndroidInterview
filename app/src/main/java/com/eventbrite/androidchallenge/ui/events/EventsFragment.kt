package com.eventbrite.androidchallenge.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventbrite.androidchallenge.R
import com.eventbrite.androidchallenge.data.events.model.EventDto
import com.eventbrite.androidchallenge.databinding.EventsFragmentBinding
import com.eventbrite.androidchallenge.repository.EventsRepository
import com.eventbrite.androidchallenge.ui.adapters.EventsAdapter
import com.eventbrite.androidchallenge.ui.base.BaseFragment
import com.eventbrite.androidchallenge.ui.state.ResourceState
import com.eventbrite.androidchallenge.utils.hide
import com.eventbrite.androidchallenge.utils.show
import com.eventbrite.androidchallenge.utils.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class EventsFragment : BaseFragment<EventsFragmentBinding, EventsViewModel>() {

    override val viewModel: EventsViewModel by viewModels(factoryProducer = { EventsViewModelFactory() })
    private val eventsAdapter by lazy { EventsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        collectObserver()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EventsFragmentBinding =
        EventsFragmentBinding.inflate(inflater, container, false)

    private fun setupRecycleView() = with(binding) {
        rvEvents.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun collectObserver() = lifecycleScope.launch {
        viewModel.listEvents.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is ResourceState.Sucess -> {
                    resource.data?.let { values ->
                        binding.progressCircular.hide()
                        eventsAdapter.events = values.events.toList() as MutableList<EventDto>
                    }
                }
                is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    resource.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("EventsFragment").e("Error -> $message")
                    }
                }

                is ResourceState.Loading -> {
                    binding.progressCircular.show()
                }
                else -> {
                    Timber.tag("EventsFragment").e("Error -> ResourceState with state")
                }
            }
        })
    }

}



