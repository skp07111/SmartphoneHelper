package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class C_PhoneBook_SearchActivity : AppCompatActivity(){
    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )
    lateinit var voice_search: Button
    private lateinit var popupContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonebook_search)
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
            }// 초기화 끝
        voice_search = findViewById<Button>(R.id.btn_voice_search)
        tts?.speak(
            "키보드 자판을 쳐서 전화하길 원하는 사람의 이름을 검색할 수 있습니다. 빨간 테두리의 버튼을 눌러주세요. ", TextToSpeech.QUEUE_FLUSH, null, null)
        showPopupMessage("키보드 자판을 쳐서 전화하길 원하는 사람의 이름을 검색할 수 있습니다. 빨간 테두리의 버튼을 눌러주세요.")
        //
        voice_search.setOnClickListener {
            tts?.speak(
                "이 음성 검색 버튼을 누르고 전화하길 원하는 사람의 이름을 말하면 자동으로 검색됩니다 ", TextToSpeech.QUEUE_FLUSH, null, null
            )
            val intent = Intent(this, C_PhoneBook_Search2Activity::class.java)
            startActivity(intent)
        }
    }
    private fun showPopupMessage(message: String) {
        popupContainer = findViewById(R.id.popupContainer)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }

    private fun closePopupMessage() {
        popupContainer.visibility = View.GONE
    }
}