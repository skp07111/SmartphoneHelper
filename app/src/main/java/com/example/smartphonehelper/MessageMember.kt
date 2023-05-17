package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MessageMember : AppCompatActivity() {
//메세지 보낼 사람을 선택하는 화면
    lateinit var choose :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messagemember)

        //보낼 사람을 정하면 메세지 입력하는 화면으로 전환
        choose= findViewById<Button>(R.id.btn_choose)
        choose.setOnClickListener{
            val intent= Intent(this, MessageWrite::class.java)
            startActivity(intent)
        }
    }
}