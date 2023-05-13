package com.example.smartphonehelper

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
    }
}