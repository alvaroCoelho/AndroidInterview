package com.eventbrite.androidchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eventbrite.androidchallenge.databinding.MainActivityBinding
import com.eventbrite.androidchallenge.ui.events.EventsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var naviHostFragment: NavHostFragment
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews(binding)
    }

    private fun initViews(binding: MainActivityBinding) {
        naviHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
       /* val navController = naviHostFragment.navController

        binding.fragmentContainerView.apply {
            setupWithNavController(navController)

        }*/
    }
}
