package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {

    lateinit var moveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        moveButton = findViewById(R.id.moveButton)
        moveButton.setOnClickListener(onClickListener)


        // 최초 실행 여부를 판단 ->>>
        val pref = getSharedPreferences("checkFirst", MODE_PRIVATE)
        val checkFirst = pref.getBoolean("checkFirst", false)

        // false일 경우 최초 실행
        if (!checkFirst) {
            // 앱 최초 실행시 하고 싶은 작업
            val editor = pref.edit()
            editor.putBoolean("checkFirst", true)
            editor.apply()
            finish()
            val intent = Intent(this@CameraActivity, CameraTutorialActivity::class.java)
            startActivity(intent)
        }
    }

    var onClickListener = View.OnClickListener {
        val intent = Intent(this@CameraActivity, CameraTutorialActivity::class.java)
        startActivity(intent)
        finish()
    }
}