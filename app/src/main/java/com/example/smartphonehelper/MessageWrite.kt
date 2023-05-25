package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MessageWrite : AppCompatActivity(){
    lateinit var send: Button
    //종이비행기 버튼 누르면 화면전환(메세지 보내기 성공)
    override fun onCreate(savedInstanceState: Bundle?) {
        send = findViewById<Button>(R.id.btn_send)
        send.setOnClickListener{
            val intent= Intent(this, MessageSend::class.java)
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writethemessage)
    }
}