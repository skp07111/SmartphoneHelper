package com.npnc.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView

class YoutubeMainActivity : AppCompatActivity() {

    lateinit var menuButton: Button
    lateinit var searchButton: Button
    lateinit var videoButton: Button

    private lateinit var popupContainer: RelativeLayout

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

        showPopupMessage("빨간색 테두리의 버튼들을 하나씩 눌러보세요!")
    }

    private fun showPopupMessage(message: String) {
        popupContainer = findViewById(R.id.popupContainer)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}