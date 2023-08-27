package com.example.videocallapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.videocallapp.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        hideStatusBar()
        initAllUi()

    }

    private fun initAllUi() {
        Handler(Looper.getMainLooper()).postDelayed({
            // This code will be executed after the specified delay
            // You can place your desired actions here
            // For example, navigate to another activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }, 3000)


    }

    private fun hideStatusBar() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}