package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KakaoPhoto1: AppCompatActivity() {
    lateinit var actbutton3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_send_photo)

        actbutton3 = findViewById<Button>(R.id.act_btn3)
        actbutton3.setOnClickListener {
            val intent = Intent(this, KakaoMessage2::class.java)
            startActivity(intent)
        }
    }

}