package com.npnc.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class KakaoMessage2 : AppCompatActivity() {
    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    lateinit var actbutton2: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_sendmessage2)

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
                "하고싶은 말은 적은 후 종이비행기 그림을 누르면 메세지가 전송됩니다.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        //전송버튼 누르면 화면전환
        actbutton2 = findViewById<ImageButton>(R.id.act_btn2)
        actbutton2.setOnClickListener {
            val intent = Intent(this, KakaoMessage3::class.java)
            startActivity(intent)
        }

    }
}