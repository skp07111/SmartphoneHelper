package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class C_MakingCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_makingcall)
        //3초후 통화 연결 되어 통화중 화면(C_OnThePhoneCallActivity)으로 전환
        //Handler().postDelayed({ startActivity(Intent(StartActivity::class.java, MainActivity::class.java)) }, 2000L)
    }
}