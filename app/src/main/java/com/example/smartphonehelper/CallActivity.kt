package com.example.smartphonehelper

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
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
    private lateinit var popupContainer: LinearLayout

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

            showPopupMessage("네모 테두리를 눌러주세요.\n 각 기능을 음성으로 설명해드려요!")

            // 통화 버튼 눌렀을 때 통화거는중 화면 (C_MakingCallActiviy)으로 전환
            call.setOnClickListener {
                tts?.speak(
                    "5초후 통화중 화면으로 전환됩니다", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("전화번호를 입력하고 이 버튼을 누르면 전화가 걸립니다.\n 상대방이 전화를 받을 때까지 기다리면 됩니다. \n 통화중 화면으로 전환됩니다")
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, C_OnThePhoneCallActivity::class.java))
                }, 5000)
            }
            // 영상통화 버튼 눌렀을 때 영상통화 거는 화면(VC_MakingCallActivity)으로 전환
            videoCall.setOnClickListener {
                val intent = Intent(this, VC_MakingCallActivity::class.java)
                startActivity(intent)
                tts?.speak(
                    "전화번호를 입력하고 이 버튼을 누르면 영상 전화가 걸립니다. 전화를 끊고 싶으면 빨간색 수화기 버튼을 눌러주세요", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("전화번호를 입력하고 이 버튼을 누르면 영상 전화가 걸립니다. 전화를 끊고 싶으면 빨간색 수화기 버튼을 눌러주세요")


            }
            search.setOnClickListener{
                tts?.speak("이 버튼을 누르면 연락처를 검색할 수 있습니다.", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("이 버튼을 누르면 연락처를 검색할 수 있습니다. \n 연락처 검색 화면으로 전환됩니다.")
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, C_PhoneBook_SearchActivity::class.java))
                }, 3000)

            }
            //지우기 버튼 눌렀을 때 tts 안내
            back.setOnClickListener{
                tts?.speak("이 버튼을 누르면 입력한 전화번호를 지울 수 있습니다", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("이 버튼을 누르면 입력한 전화번호를 지울 수 있습니다")

            }
            //지우기 버튼 눌렀을 때 tts 안내
            keypad.setOnClickListener{
                tts?.speak("이 버튼을 누르면 지금 보시는 것과 같이 전화번호를 입력할 수 있는 숫자창이 뜹니다", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("이 버튼을 누르면 지금 보시는 것과 같이 전화번호를 입력할 수 있는 숫자창이 뜹니다")

            }
            //최근 통화 버튼 눌렀을때
            recentCall.setOnClickListener {
                tts?.speak("이 버튼을 누르면 최근에 통화한 사람의 목록을 볼 수 있고, 그 사람의 이름을 누르면 바로 통화를 할 수 있습니다. ", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("이 버튼을 누르면 최근에 통화한 사람의 목록을 볼 수 있고, 그 사람의 이름을 누르면 바로 통화를 할 수 있습니다. \n 최근 통화기록 화면으로 이동합니다")
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, C_RecentCallActivity::class.java))
                }, 5000)
            }
            //연락처 눌렀을 때
            phoneBook.setOnClickListener {
                tts?.speak("이 버튼을 누르면 전화번호를 확인 할 수 있습니다.", TextToSpeech.QUEUE_FLUSH, null, null)
                showPopupMessage("이 버튼을 누르면 연락처 화면으로 이동할 수 있습니다. \n 연락처 화면으로 전환됩니다.")
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, C_PhoneBookActivity::class.java))
                }, 3000)
            }


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
