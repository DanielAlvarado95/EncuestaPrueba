package com.danielalvarado.encuesta.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Thread.sleep(2000)
        screenSplash.setKeepOnScreenCondition{
            true
        }//screenSplash

        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
        finish()

    }//onCreate
}//MainActivity