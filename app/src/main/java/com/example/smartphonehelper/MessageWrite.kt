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

class MessageWrite : AppCompatActivity(){
    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    lateinit var send: ImageButton
    //종이비행기 버튼 누르면 화면전환(메세지 보내기 성공)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writethemessage)

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
                "우리 딸에게 문자가 와있네요." +
                        "답장을 하려면 메세지를 작성한 후에 종이비행기 그림을 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null
            )
        }

        send = findViewById<ImageButton>(R.id.btn_send)
        send.setOnClickListener{
            val intent= Intent(this, MessageSend::class.java)
            startActivity(intent)
        }
    }
}