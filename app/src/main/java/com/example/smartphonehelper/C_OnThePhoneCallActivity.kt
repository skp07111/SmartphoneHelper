package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class C_OnThePhoneCallActivity : AppCompatActivity() {
    lateinit var stopCall: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_onthephone)
        stopCall = findViewById<Button>(R.id.btn_stopCall)

        // 통화 끊기 버튼을 누르면 통화종료되어 CallActivity 화면으로 전환
        stopCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }
    }
}