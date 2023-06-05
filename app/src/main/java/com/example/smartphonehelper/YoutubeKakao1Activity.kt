package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class YoutubeKakao1Activity : AppCompatActivity() {
    lateinit var selectButton : Button

    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_kakao1)

        showPopupMessage2("김연정 씨에게 동영상 링크를 보내봅시다. 김연정 씨를 선택하세요.")

        selectButton = findViewById<Button>(R.id.youtube_select)

        selectButton.setOnClickListener {
            var intent = Intent(this, YoutubeKakao2Activity::class.java)
            startActivity(intent)
        }
    }

    private fun showPopupMessage2(message: String) {
        popupContainer = findViewById(R.id.popupContainer)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}