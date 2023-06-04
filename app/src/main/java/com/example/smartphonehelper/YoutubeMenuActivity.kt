package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class YoutubeMenuActivity : AppCompatActivity() {
    
    lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_menu)

        shareButton = findViewById<Button>(R.id.youtube_share)

        shareButton.setOnClickListener() {
            var intent = Intent(this, YoutubeShareActivity::class.java)
            startActivity(intent)
        }
    }
}