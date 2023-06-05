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


class MessageActivity : AppCompatActivity(){  //메세지보내기 기능 메인화면
    lateinit var msgmenu: ImageButton
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
        setContentView(R.layout.activity_message)

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
                "메세지를 보내려면 기존에 있던 대화방에 들어가거나 직접 번호를 입력하여 메세지를 보낼 수 있습니다." +
                        "아래의 십자 버튼을 눌러 대화방을 추가해봅시다.", TextToSpeech.QUEUE_FLUSH, null, null
            )
        }

        msgmenu = findViewById<ImageButton>(R.id.btn_msgmenu)
        msgmenu.setOnClickListener{
            val intent= Intent(this, MessageWrite::class.java)
            startActivity(intent)
        }
    }
}