package com.npnc.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mainCall: Button
    lateinit var mainVideoCall: Button
    lateinit var mainMessage: Button
    lateinit var mainCamera: Button
    lateinit var mainGallery: Button
    lateinit var mainYoutube: Button
    lateinit var mainKakaoTalk: Button
    lateinit var mainPlayStore: Button
    lateinit var mainHelper: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        // 플레이스토어 버튼
        mainPlayStore = findViewById<Button>(R.id.button_playstore)
        //구글 어시스턴스 버튼
        mainHelper = findViewById<Button>(R.id.button_helper)

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

        // 유튜브 버튼 누르면 YoutubeMainActivity 전환
        mainYoutube.setOnClickListener() {
            var intent = Intent(this, YoutubeMainActivity::class.java)
            startActivity(intent)
        }

        // 카카오톡 버튼 누르면 KakaoMain 전환
        mainKakaoTalk.setOnClickListener() {
            var intent = Intent(this, KakaoMain::class.java)
            startActivity(intent)
        }

        // 플레이스토어 버튼 누르면 PlayStoreActivity 전환
        mainPlayStore.setOnClickListener() {
            var intent = Intent(this, PlayStoreActivity::class.java)
            startActivity(intent)
        }

        // 영상통화 버튼 누르면 VideoCallActivity로 전환
        mainHelper.setOnClickListener() {
            var intent = Intent(this, Helper::class.java)
            startActivity(intent)
        }
    }
}