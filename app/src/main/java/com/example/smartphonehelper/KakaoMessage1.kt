package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KakaoMessage1 : AppCompatActivity() {
    lateinit var actbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_sendmessage1)

        //하단의 +버튼을 누르면 키패드 생성
        actbutton = findViewById<Button>(R.id.act_btn)
        actbutton.setOnClickListener {
            val intent = Intent(this, KakaoMessage2::class.java)
            startActivity(intent)
        }
    }
}