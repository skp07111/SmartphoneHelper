package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MessageActivity : AppCompatActivity(){  //메세지보내기 기능 메인화면
    lateinit var msgmenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        msgmenu = findViewById<Button>(R.id.btn_msgmenu)
        msgmenu.setOnClickListener{
            val intent= Intent(this, MessageWrite::class.java)
            startActivity(intent)
        }
    }
}