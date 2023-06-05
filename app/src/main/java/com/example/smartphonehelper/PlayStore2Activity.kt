package com.example.smartphonehelper

import android.Manifest
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

class PlayStore2Activity : AppCompatActivity() {
    private lateinit var popupContainer: RelativeLayout

    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_store2)

        //tts 초기화 설정
        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
            tts?.speak("검색창에서 설치하고 싶은 앱을 직접 입력할 수 있습니다. 혹은 왼쪽 상단에 마이크 모양 버튼을 눌러서 음성 검색을 할 수 있어요. 한 번 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        showPopupMessage("검색창에서 설치하고 싶은 앱을\n직접 입력할 수 있습니다.\n혹은 왼쪽 상단에 [마이크] 모양 버튼을 눌러서\n음성 검색을 할 수 있어요.\n한 번 눌러보세요.")

        val playStoreMikeIcon = findViewById<ImageButton>(R.id.playStoreMikeIcon)
        playStoreMikeIcon.setOnClickListener {
            val intent = Intent(this, PlayStore3Activity::class.java)
            startActivity(intent)
        }
    }

    private fun showPopupMessage(message: String) {
        popupContainer = findViewById(R.id.popupContainer)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }

    private fun closePopupMessage() {
        popupContainer.visibility = View.GONE
    }
}