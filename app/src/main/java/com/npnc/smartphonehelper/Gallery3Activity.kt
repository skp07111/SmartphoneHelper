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

class Gallery3Activity : AppCompatActivity() {
    private lateinit var popupContainer: RelativeLayout

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
        setContentView(R.layout.activity_gallery3)

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
            tts?.speak("하단 중앙에 있는 사진공유라고 쓰여진 버튼을 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null)
        }

        showPopupMessage("사진을 공유하고 싶다면\n하단 중앙에 있는\n[사진공유] 버튼을 눌러보세요.")

        val galleryAlbumPicShare = findViewById<ImageButton>(R.id.galleryAlbumPicShare)
        galleryAlbumPicShare.setOnClickListener {
            val intent = Intent(this, GalleryShareActivity::class.java)
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