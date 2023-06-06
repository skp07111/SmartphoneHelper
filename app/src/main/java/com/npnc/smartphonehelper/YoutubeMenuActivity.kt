package com.npnc.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView

class YoutubeMenuActivity : AppCompatActivity() {
    
    lateinit var shareButton: Button

    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_menu)

        shareButton = findViewById<Button>(R.id.youtube_shareButton)

        shareButton.setOnClickListener() {
            var intent = Intent(this, YoutubeShareActivity::class.java)
            startActivity(intent)
        }

        showPopupMessage("빨간색 테두리의 버튼을 눌러보세요!")
    }

    private fun showPopupMessage(message: String) {
        popupContainer = findViewById(R.id.popupContainer3)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}