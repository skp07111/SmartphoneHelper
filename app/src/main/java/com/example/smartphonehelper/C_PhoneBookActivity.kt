package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class C_PhoneBookActivity : AppCompatActivity() {
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )
    lateinit var add_phone: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonebook)
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
            add_phone = findViewById<Button>(R.id.btn_add_phone)

            tts?.speak(
                "더하기 모양 버튼을 눌러주세요 ", TextToSpeech.QUEUE_FLUSH, null, null)

            //
            add_phone.setOnClickListener {
                tts?.speak(
                    "이 더하기 모양 버튼을 누르면 연락처를 저장할 수 있습니다. 전화번호와 이름등을 입력해 주세요", TextToSpeech.QUEUE_FLUSH, null, null
                )
                val intent = Intent(this, C_PhoneBookAddActivity::class.java)
                startActivity(intent)
            }
        }
    }
}