package com.example.videocallapp.ui.activity

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.videocallapp.R
import com.example.videocallapp.alladapter.PulseAdapter
import com.example.videocallapp.api.ApiService
import com.example.videocallapp.api.RetrofitClient
import com.example.videocallapp.databinding.ActivityMainBinding
import com.example.videocallapp.repo.VideoRepo
import com.example.videocallapp.viewmodel.VideoViewModel
import com.example.videocallapp.viewmodel.VideoViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PulseAdapter
    private lateinit var videoViewModel: VideoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation .setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> navController.navigate(R.id.fragment_home)
                R.id.navigation_live_call -> navController.navigate(R.id.fragment_live_call)
                R.id.navigation_voice_call -> navController.navigate(R.id.fragment_voice_call)
                R.id.navigation_profile -> navController.navigate(R.id.fragment_profile)
            }
            true
        }

    }

    private fun setupRecyclerview() {
        val apiinterface = RetrofitClient.getInstance().create(ApiService::class.java)

        val videoRepo = VideoRepo(apiinterface)

        videoViewModel =
            ViewModelProvider(this, VideoViewModelFactory(videoRepo))[VideoViewModel::class.java]
        videoViewModel.video.observe(this) {
            Log.d("TAG", "onCreate: ${it.get(0).video_url}")
            adapter = PulseAdapter(it , this)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.pulseRecyclerview)
            binding.pulseRecyclerview.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            binding.pulseRecyclerview.adapter = adapter

        }
    }
}