package com.example.checkoutpointapplication.views.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.checkoutpointapplication.R
import com.example.checkoutpointapplication.views.history.HistoryActivity
import com.example.checkoutpointapplication.views.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupDelay()
    }

    private fun setupDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, 2000)
    }
}