package com.npnc.smartphonehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class YoutubeKakao3Activity : AppCompatActivity() {
    private lateinit var popupContainer: RelativeLayout
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_kakao3)

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

        showPopupMessage2("성공! 카카오톡으로 김연정 씨에게 동영상 링크를 공유했습니다.")

        openTTS()
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
                "성공! 카카오톡으로 김연정 씨에게 동영상 링크를 공유했습니다.",
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