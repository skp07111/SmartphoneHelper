package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView

class Gallery2Activity : AppCompatActivity() {

    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery2)

        showPopupMessage("날짜별로 사진을 볼 수 있습니다.")

        val galleryAlbumIcon = findViewById<ImageButton>(R.id.galleryAlbumPicAlbumTextIcon)
        galleryAlbumIcon.setOnClickListener {
            finish()
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