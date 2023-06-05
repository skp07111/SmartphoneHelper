package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class KakaoPhoto4 : AppCompatActivity() {
    lateinit var next : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_sendphoto4)

        next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            val intent = Intent(this, KakaoPhoto1::class.java)
            startActivity(intent)
        }
    }


}