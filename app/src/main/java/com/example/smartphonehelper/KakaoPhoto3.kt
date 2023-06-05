package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class KakaoPhoto3 : AppCompatActivity() {
    lateinit var send: ImageButton
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
        setContentView(R.layout.activity_kakao_sendphoto3)

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
            tts?.speak(
                "보내고싶은 사진을 누른 후 종이비행기 그림을 누르면 전송됩니다." +
                        "더 많은 사진과 동영상을 보내려면 하단의 전체 버튼을 누르면 됩니다.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        send = findViewById<ImageButton>(R.id.imageButton3)
        send.setOnClickListener {
            val intent = Intent(this, KakaoPhoto4::class.java)
            startActivity(intent)
        }
    }
}