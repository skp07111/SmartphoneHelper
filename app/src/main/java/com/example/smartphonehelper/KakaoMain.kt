package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.Locale

class KakaoMain : AppCompatActivity() {
    lateinit var btntoprofile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakaofriends)

        //프로필을 터치하면 프로필화면으로 화면전환
        btntoprofile = findViewById<Button>(R.id.btn_to_profile)
        btntoprofile.setOnClickListener {
            val intent = Intent(this, KakaoProfile::class.java)
            startActivity(intent)
        }

    }
}