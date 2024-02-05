package com.example.myapp_zkouska

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val btnVozidla = findViewById<Button>(R.id.btnVozidla)
        btnVozidla.setOnClickListener {
            Intent(this, ActivityVozidla::class.java).also {
                startActivity(it)
            }
        }

        val btnSeznamStk = findViewById<Button>(R.id.btnSeznamStk)
        btnSeznamStk.setOnClickListener {
            Intent(this, ActivityStaniceStk::class.java).also {
                startActivity(it)
            }
        }
    }
}