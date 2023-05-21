package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class C_OnThePhoneCallActivity : AppCompatActivity() {
    lateinit var b_stopCall: ImageButton
    lateinit var b_record: Button
    lateinit var b_videocall: Button
    lateinit var b_bluetooth: Button
    lateinit var b_speaker: Button
    lateinit var b_blockvoice: Button
    lateinit var b_keypad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_onthephone)
        b_stopCall = findViewById<ImageButton>(R.id.btn_stopCall)
        b_record = findViewById<Button>(R.id.button_rocord)
        b_videocall = findViewById<Button>(R.id.button_videocall)
        b_bluetooth = findViewById<Button>(R.id.button_bluetooth)
        b_speaker = findViewById<Button>(R.id.button_speaker)
        b_blockvoice = findViewById<Button>(R.id.button_blockvoice)
        b_keypad = findViewById<Button>(R.id.button_keypad)
        // 통화 끊기 버튼을 누르면 통화종료되어 CallActivity 화면으로 전환
        b_stopCall.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }
    }
}