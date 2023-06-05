package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class YoutubeMainActivity : AppCompatActivity() {

    lateinit var menuButton: Button
    lateinit var searchButton: Button
    lateinit var videoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_main)

        menuButton = findViewById<Button>(R.id.youtube_menuButton)
        searchButton = findViewById<Button>(R.id.youtube_searchButton)
        videoButton = findViewById<Button>(R.id.youtube_videoButton)

        menuButton.setOnClickListener() {
            var intent = Intent(this, YoutubeMenuActivity::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener() {
            var intent = Intent(this, YoutubeSearchActivity::class.java)
            startActivity(intent)
        }

        videoButton.setOnClickListener() {
            var intent = Intent(this, YoutubeVideoActivity::class.java)
            startActivity(intent)
        }
    }
}