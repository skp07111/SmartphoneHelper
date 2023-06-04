package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )
    lateinit var mainCall: Button
    lateinit var mainVideoCall: Button
    lateinit var mainMessage: Button
    lateinit var mainCamera: Button
    lateinit var mainGallery: Button
    lateinit var mainYoutube: Button
    lateinit var mainKakaoTalk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
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
            //tts 말하기
            tts?.speak("안녕하세요. 저희는 스마트폰 도우미입니다. 지금부터 스마트폰 사용법에 대해 알려드리겠습니다.", TextToSpeech.QUEUE_FLUSH, null, null)

            // 영상통화 버튼
            mainVideoCall = findViewById<Button>(R.id.button_videoCall)
            // 메시지 버튼
            mainMessage = findViewById<Button>(R.id.button_message)
            // 카메라 버튼
            mainCamera = findViewById<Button>(R.id.button_camera)
            // 갤러리 버튼
            mainGallery = findViewById<Button>(R.id.button_gallery)
            // 유튜브 버튼
            mainYoutube = findViewById<Button>(R.id.button_youtube)
            // 카카오톡 버튼
            mainKakaoTalk = findViewById<Button>(R.id.button_kakaotalk)

            // 영상통화 버튼 누르면 VideoCallActivity로 전환
            mainVideoCall.setOnClickListener() {
                var intent = Intent(this, CallActivity::class.java)
                startActivity(intent)
            }

            // 메시지 버튼 누르면 MessageActivity로 전환
            mainMessage.setOnClickListener() {
                var intent = Intent(this, MessageActivity::class.java)
                startActivity(intent)
            }

            // 카메라 버튼 누르면 CameraActivity로 전환
            mainCamera.setOnClickListener() {
                var intent = Intent(this, CameraActivity::class.java)
                startActivity(intent)
            }

            // 갤러리 버튼 누르면 GalleryActivity 전환
            mainGallery.setOnClickListener() {
                var intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
            }

            mainYoutube.setOnClickListener() {
                var intent = Intent(this, YoutubeMainActivity::class.java)
                startActivity(intent)
            }

            // 카카오톡 버튼 누르면 KakaoMain 전환
            mainKakaoTalk.setOnClickListener() {
                var intent = Intent(this, KakaoMain::class.java)
                startActivity(intent)
            }
        }

    }
}