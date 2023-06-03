package com.example.smartphonehelper

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity : AppCompatActivity() {

    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        showPopupMessage("앨범은 사진을 각각 분류하여\n모아주는 곳입니다.\n분류 없이 모든 사진을 보고 싶으시면\n왼쪽 하단에 있는 [사진] 글씨를\n눌러보세요.")

        val galleryPictureIcon = findViewById<ImageButton>(R.id.galleryPictureIcon)
        galleryPictureIcon.setOnClickListener {
            val intent = Intent(this, Gallery2Activity::class.java)
            startActivity(intent)
        }
    }

    private fun showPopupMessage(message: String) {
        popupContainer = findViewById(R.id.popupContainer)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }

    private fun closePopupMessage() {
        popupContainer.visibility = View.GONE
    }

}