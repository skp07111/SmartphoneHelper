package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class C_OnThePhoneCallActivity : AppCompatActivity() {
    lateinit var b_stopCall: ImageButton
    lateinit var b_record: Button
    lateinit var b_videocall: Button
    lateinit var b_bluetooth: Button
    lateinit var b_speaker: Button
    lateinit var b_blockvoice: Button
    lateinit var b_keypad: Button
    private lateinit var popupContainer: LinearLayout
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
        setContentView(R.layout.activity_call_onthephone)
        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }

        }//초기화끝
        b_stopCall = findViewById<ImageButton>(R.id.btn_stopCall)
        b_record = findViewById<Button>(R.id.button_rocord)
        b_videocall = findViewById<Button>(R.id.button_videocall)
        b_bluetooth = findViewById<Button>(R.id.button_bluetooth)
        b_speaker = findViewById<Button>(R.id.button_speaker)
        b_blockvoice = findViewById<Button>(R.id.button_blockvoice)
        b_keypad = findViewById<Button>(R.id.button_keypad)
        showPopupMessage("빨간 테두리 버튼을 눌러주시면 기능을 알려드립니다 \n 통화를 끊고 싶으시면 빨간 수화기 버튼을 눌러주세요")
        tts?.speak(
            "통화를 끊고 싶으시면 빨간 수화기 버튼을 눌러주세요.",
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        )
        // 통화 끊기 버튼을 누르면 통화 종료되어 CallActivity 화면으로 전환
        b_stopCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
            //tts 말하기
            tts?.speak(
                "통화가 종료되었습니다. 전화를 끊고 싶을 때 이 빨간색 전화기 모양을 누르시면 됩니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }
        b_speaker.setOnClickListener {
            //tts 말하기
            tts?.speak(
                "스피커 버튼입니다. 스피커 버튼은 상대방의 목소리가 크게 들리게 하고, 전화를 얼굴에 붙이지 않고도 멀리서 말해도 상대방이 들을 수 있습니다. ",
                TextToSpeech.QUEUE_FLUSH, null, null)
            showPopupMessage("스피커 버튼입니다. 스피커 버튼은 상대방의 목소리가 크게 들리게 하고, 전화를 얼굴에 붙이지 않고도 멀리서 말해도 상대방이 들을 수 있습니다.")
        }
        b_videocall.setOnClickListener {
            //tts 말하기
            tts?.speak(
                "영상통화 버튼입니다. 영상통화 버튼은 상대방의 얼굴을 보며 통화할 수 있습니다. 이 버튼을 누르면 영상통화로 전환됩니다. ",
                TextToSpeech.QUEUE_FLUSH, null, null)
            showPopupMessage("영상통화 버튼입니다. 영상통화 버튼은 상대방의 얼굴을 보며 통화할 수 있습니다. 이 버튼을 누르면 영상통화로 전환됩니다.")

        }
        b_record.setOnClickListener {
            //tts 말하기
            tts?.speak(
                "녹음버튼입니다. 이 버튼을 누르면 상대방과 내가 통화하는 소리를 녹음할 수 있습니다. 이 버튼을 누르고 정사각형 버튼을 누르면 통화녹음이 저장됩니다. ",
                TextToSpeech.QUEUE_FLUSH, null, null)
            showPopupMessage("녹음버튼입니다. 이 버튼을 누르면 상대방과 내가 통화하는 소리를 녹음할 수 있습니다. 이 버튼을 누르고 정사각형 버튼을 누르면 통화녹음이 저장됩니다.")

        }
        b_blockvoice.setOnClickListener {
            //tts 말하기
            tts?.speak(
                "내 소리 차단 버튼입니다. 이 버튼을 누르면 상대방에게 내 목소리를 안들리게 할 수 있습니다. 다시 누르면 내소리 차단이 해제됩니다. ",
                TextToSpeech.QUEUE_FLUSH, null, null)
            showPopupMessage("내 소리 차단 버튼입니다. 이 버튼을 누르면 상대방에게 내 목소리를 안들리게 할 수 있습니다. 다시 누르면 내소리 차단이 해제됩니다.")

        }
        b_keypad.setOnClickListener {
            //tts 말하기
            tts?.speak(
                "키패드 버튼입니다. 통화 중에 이 버튼을 누르면 전화번호 숫자를 입력하실 수 있습니다.",
                TextToSpeech.QUEUE_FLUSH, null, null)
            showPopupMessage("키패드 버튼입니다. 통화 중에 이 버튼을 누르면 전화번호 숫자를 입력하실 수 있습니다.")

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