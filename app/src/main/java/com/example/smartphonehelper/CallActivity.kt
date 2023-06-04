package com.example.smartphonehelper

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
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
    lateinit var search: Button
    lateinit var keypad: Button
    lateinit var videoCall: Button
    lateinit var call: Button
    lateinit var back: Button
    lateinit var recentCall: Button
    lateinit var phoneBook: Button


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
            search = findViewById<Button>(R.id.btn_phone_search)
            keypad = findViewById<Button>(R.id.btn_keypad)
            videoCall = findViewById<Button>(R.id.btn_video_call)
            call = findViewById<Button>(R.id.btn_call)
            back = findViewById<Button>(R.id.btn_back)
            recentCall = findViewById<Button>(R.id.btn_recent_call)
            phoneBook = findViewById<Button>(R.id.btn_phone_book)
            tts?.speak(
                "각 버튼을 눌러주시면, 어떤 기능인지 설명해드리겠습니다", TextToSpeech.QUEUE_FLUSH, null, null)
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
            search.setOnClickListener{
                tts?.speak("이 버튼을 누르면 연락처를 검색할 수 있습니다.", TextToSpeech.QUEUE_FLUSH, null, null)
            }
            //지우기 버튼 눌렀을 때 tts 안내
            back.setOnClickListener{
                tts?.speak("이 버튼을 누르면 입력한 전화번호를 지울 수 있습니다", TextToSpeech.QUEUE_FLUSH, null, null)
            }
            //최근 통화 버튼 눌렀을때
            recentCall.setOnClickListener {
                val intent = Intent(this, C_RecentCallActivity::class.java)
                startActivity(intent)
            }
            //연락처 눌렀을 때
            phoneBook.setOnClickListener {
                val intent = Intent(this, C_PhoneBookActivity::class.java)
                startActivity(intent)
            }


        }


    }
}