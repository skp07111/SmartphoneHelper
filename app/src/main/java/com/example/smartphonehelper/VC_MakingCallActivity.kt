package com.example.smartphonehelper

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VC_MakingCallActivity  : AppCompatActivity() {

    lateinit var vc_camera: Button
    lateinit var vc_change: Button
    lateinit var vc_off: Button
    lateinit var vc_block: Button
    lateinit var vc_speaker: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videocall_makingcall)

        vc_camera = findViewById<Button>(R.id.icon_vc_camera)
        vc_change = findViewById<Button>(R.id.icon_vc_change)
        vc_off = findViewById<Button>(R.id.icon_vc_off)
        vc_block = findViewById<Button>(R.id.icon_vc_block)
        vc_speaker = findViewById<Button>(R.id.icon_vc_speaker)
    }
}