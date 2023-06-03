package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KakaoMessage2 : AppCompatActivity() {
    lateinit var actbutton2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_sendmessage2)

        //전송버튼 누르면 화면전환
        actbutton2 = findViewById<Button>(R.id.act_btn2)
        actbutton2.setOnClickListener {
            val intent = Intent(this, KakaoMessage3::class.java)
            startActivity(intent)
        }
    }
}