package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class YoutubeMainActivity : AppCompatActivity() {

    lateinit var menuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_main)

        menuButton = findViewById<Button>(R.id.youtube_menu)

        menuButton.setOnClickListener() {
            var intent = Intent(this, YoutubeMenuActivity::class.java)
            startActivity(intent)
        }
    }
}