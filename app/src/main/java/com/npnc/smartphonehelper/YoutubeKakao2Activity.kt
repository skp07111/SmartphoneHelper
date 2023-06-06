package com.npnc.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class YoutubeKakao2Activity : AppCompatActivity() {
    lateinit var kakaocheckButton : ImageButton

    private lateinit var popupContainer: RelativeLayout
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_kakao2)

        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
        }

        showPopupMessage2("확인 버튼을 눌러보세요!")

        openTTS()

        kakaocheckButton = findViewById<ImageButton>(R.id.icon_kakaocheck)

        kakaocheckButton.setOnClickListener {
            var intent = Intent(this, YoutubeKakao3Activity::class.java)
            startActivity(intent)
        }
    }

    private fun openTTS() {
        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
            //tts 말하기
            tts?.speak(
                "확인 버튼을 눌러보세요.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }
    }

    private fun showPopupMessage2(message: String) {
        popupContainer = findViewById(R.id.popupContainer)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}