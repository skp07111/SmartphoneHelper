package com.npnc.smartphonehelper

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class Gallery2Activity : AppCompatActivity() {

    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    private lateinit var popupContainer: RelativeLayout

    private lateinit var button1: ImageButton
    private lateinit var button2: ImageButton
    private lateinit var button3: ImageButton
    private lateinit var button4: ImageButton
    private lateinit var button5: ImageButton
    private lateinit var button6: ImageButton
    private lateinit var button7: ImageButton
    private lateinit var button8: ImageButton
    private lateinit var button9: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery2)

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
            tts?.speak("날짜별로 사진을 볼 수 있습니다. 크게 보려면 사진을 한 번 눌러보세요. 이 화면에서 사진은 회색 사각형 부분입니다.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        showPopupMessage("날짜별로 사진을 볼 수 있습니다.\n크게 보려면 사진을 한 번 눌러보세요.\n(회색 사각형들 중 아무거나)")

        val galleryAlbumIcon = findViewById<ImageButton>(R.id.galleryAlbumPicAlbumTextIcon)
        galleryAlbumIcon.setOnClickListener {
            finish()
        }

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        button1.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button6.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button7.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button8.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
            startActivity(intent)
        }

        button9.setOnClickListener {
            val intent = Intent(this, Gallery3Activity::class.java)
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