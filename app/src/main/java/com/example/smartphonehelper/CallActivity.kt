package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class CallActivity : AppCompatActivity() {
    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    lateinit var videoCall: Button
    lateinit var call: Button
    lateinit var back: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

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

            videoCall = findViewById<Button>(R.id.video_call)
            call = findViewById<Button>(R.id.icon_call)
            back = findViewById<Button>(R.id.icon_back)
            tts?.speak(
                "전화를 걸려면 상대방의 전화번호를 누른 후에 가운데에 있는 녹색 통화버튼을 누르면 됩니다. 전화번호를 지우려면 세번째에 있는 버튼을 누르시면 됩니다.", TextToSpeech.QUEUE_FLUSH, null, null)
            tts?.speak(
                "상대방에게 전화를 걸고 싶으시면 녹색 전화기 모양 버튼을 눌러주시고, 영상통화를 걸고 싶으시면 첫번째 버튼을 눌러주세요.", TextToSpeech.QUEUE_FLUSH, null, null)
            // 통화 버튼 눌렀을 때 통화거는중 화면 (C_MakingCallActiviy)으로 전환
            call.setOnClickListener {
                val intent = Intent(this, C_MakingCallActivity::class.java)
                startActivity(intent)
            }
            // 영상통화 버튼 눌렀을 때 영상통화 거는 화면(VC_MakingCallActivity)으로 전환
            videoCall.setOnClickListener {
                val intent = Intent(this, VC_MakingCallActivity::class.java)
                startActivity(intent)
            }
        }


    }
}