package com.npnc.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class GalleryActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

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
            tts?.speak("앨범은 사진을 각각 분류하여 모아주는 곳입니다. 분류 없이 모든 사진을 보고 싶으시면 왼쪽 하단에 있는 사진 글씨를 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        showPopupMessage("앨범은 사진을 각각 분류하여\n모아주는 곳입니다.\n분류 없이 모든 사진을 보고 싶으시면\n왼쪽 하단에 있는 [사진] 글씨를\n눌러보세요.")

        val galleryPictureIcon = findViewById<ImageButton>(R.id.galleryPictureIcon)
        galleryPictureIcon.setOnClickListener {
            val intent = Intent(this, Gallery2Activity::class.java)
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