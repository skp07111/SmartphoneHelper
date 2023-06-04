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
        //메세지 메인화면에서 우측하단 버튼을 누르면 <1:1메세지보내기, 그룹채팅, 단체문자> 중에서 선택할 수 있는 창 호출
        //(단, 우리는 그 중 1:1메세지보내기만 구현)
        msgmenu.setOnClickListener{
            val intent= Intent(this, MessageWrite::class.java)
            startActivity(intent)
        }
    }
}