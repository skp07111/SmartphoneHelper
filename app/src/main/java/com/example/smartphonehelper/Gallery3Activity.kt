package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView

class Gallery3Activity : AppCompatActivity() {
    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery3)

        showPopupMessage("사진을 공유하고 싶으시면\n하단 중앙에 있는\n[사진공유] 버튼을 눌러보세요.")

        val galleryAlbumPicShare = findViewById<ImageButton>(R.id.galleryAlbumPicShare)
        galleryAlbumPicShare.setOnClickListener {
            val intent = Intent(this, GalleryShareActivity::class.java)
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