package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KakaoPhoto3 : AppCompatActivity() {
    lateinit var send:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_sendphoto3)

        send = findViewById<Button>(R.id.imageButton3)
        send.setOnClickListener {
            val intent = Intent(this, KakaoMessage3::class.java)
            startActivity(intent)
        }
    }
}