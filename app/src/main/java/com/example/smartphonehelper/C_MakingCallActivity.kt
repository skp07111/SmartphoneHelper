package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler;
import android.os.Looper

class C_MakingCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_makingcall)
        //3초후 통화 연결 되어 통화중 화면(C_OnThePhoneCallActivity)으로 전환
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, C_OnThePhoneCallActivity::class.java))
        }, 5000)
        
    }
}