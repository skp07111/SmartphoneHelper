package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.Locale

class KakaoProfile: AppCompatActivity() {
    lateinit var chat: ImageButton

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
        setContentView(R.layout.activity_kakaoprofile)
        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
            tts?.speak(
                "하단의 말풍선 그림을 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null
            )
        }
        //프로필을 터치하면 프로필화면으로 화면전환
        chat = findViewById<ImageButton>(R.id.btn_chat)
        chat.setOnClickListener {
            val intent = Intent(this, KakaoMessage1::class.java)
            startActivity(intent)
        }
    }
}