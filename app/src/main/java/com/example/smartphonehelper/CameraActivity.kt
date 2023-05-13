package com.example.smartphonehelper

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {

    lateinit var btn_camera_click: Button
    lateinit var cameraMainImageView: ImageView
    lateinit var imageViewTest: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btn_camera_click = findViewById(R.id.btn_camera_click);
        cameraMainImageView = findViewById(R.id.cameraMainImageView);

        btn_camera_click.setOnClickListener {
            imageViewTest.setImageResource(R.drawable.ic_baseline_person_24)
            Toast.makeText(this@CameraActivity,"버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()
        }



    }
}