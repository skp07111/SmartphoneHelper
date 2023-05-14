package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CallActivity : AppCompatActivity() {

    lateinit var videoCall: Button
    lateinit var call: Button
    lateinit var back: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        videoCall = findViewById<Button>(R.id.icon_video)
        call = findViewById<Button>(R.id.icon_call)
        back = findViewById<Button>(R.id.icon_back)

        //tts로 안내음성 출력
        val text = "테스트입니다".toString()
        val ttsactivity = TTSActivity()
        ttsactivity.ttsSpeak(text)


        // 통화 버튼 눌렀을 때 통화거는중 화면 (C_MakingCallActiviy)으로 전환
        call.setOnClickListener {
            val intent = Intent(this, C_MakingCallActivity::class.java)
            startActivity(intent)
        }
        // 영상통화 버튼 눌렀을 때 영상통화 거는 화면(VC_MakingCallActivity)으로 전환
        videoCall.setOnClickListener {
            val intent = Intent(this, VC_MakingCallActivity::class.java)
            startActivity(intent)

        }

    }


}