package com.example.videocallapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.videocallapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> navController.navigate(R.id.fragment_home)
                R.id.navigation_live_call -> navController.navigate(R.id.fragment_live_call)
                R.id.navigation_voice_call -> navController.navigate(R.id.fragment_voice_call)
                R.id.navigation_profile -> navController.navigate(R.id.fragment_profile)
            }
            true
        }



//        val intent = Intent(this, LiveVideoCallActivity::class.java)
//        startActivity(intent)
    }
}