package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var mainCall : Button
    lateinit var mainVideoCall : Button
    lateinit var mainMessage : Button
    lateinit var mainCamera : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 영상통화 버튼
        mainVideoCall = findViewById<Button>(R.id.button_videoCall)
        // 메시지 버튼
        mainMessage = findViewById<Button>(R.id.button_message)
        // 카메라 버튼
        mainCamera = findViewById<Button>(R.id.button_camera)

        // 영상통화 버튼 누르면 VideoCallActivity로 전환
        mainVideoCall.setOnClickListener() {
            var intent = Intent(this, VideoCallActivity::class.java)
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
    }
}