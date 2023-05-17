package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MessageMenu : AppCompatActivity(){

    //1대1대화 버튼 클릭시 메세지를 보낼 사람을 고르는 화면으로 전환하기
    lateinit var oneOnOne : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_menu)
         oneOnOne= findViewById<Button>(R.id.oneOnOne)
        oneOnOne.setOnClickListener{
            val intent= Intent(this, MessageMenu::class.java)
            startActivity(intent)
        }
    }
}