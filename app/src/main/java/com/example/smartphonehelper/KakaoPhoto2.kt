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

class KakaoPhoto2 : AppCompatActivity() {
    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )
    lateinit var photo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_sendphoto2)

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
                "갤러리에 저장된 사진을 보내려면 앨범 버튼을, 지금 이 순간을 찍어서 보내려면 카메라 버튼을 누르면 됩니다." +
                        "앨범에 저장된 사진을 보내볼까요?" +
                        "앨범 버튼을 눌러주세요.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        photo = findViewById<Button>(R.id.albumbutton)
        photo.setOnClickListener {
            val intent = Intent(this, KakaoPhoto3::class.java)
            startActivity(intent)
        }
    }

}