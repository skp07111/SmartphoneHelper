package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler;
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class C_MakingCallActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_call_makingcall)
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
            //5초후 통화 연결 되어 통화중 화면(C_OnThePhoneCallActivity)으로 전환
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, C_OnThePhoneCallActivity::class.java))
            }, 5000)
            tts?.speak("지금 상대방에게 전화가 가고 있는 중입니다. ", TextToSpeech.QUEUE_FLUSH, null, null)


        }
    }
}