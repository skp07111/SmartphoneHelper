package com.example.smartphonehelper

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val galleryAlbumImg = findViewById<ImageView>(R.id.galleryAlbumImg)
        galleryAlbumImg.setOnClickListener {
            showPopupMessage("갤러리에 있는 앨범은 사진을 분류하여 모아줍니다.")
        }
    }

    private fun showPopupMessage(message: String) {
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.popup_message, null)

        val messageTextView = layout.findViewById<TextView>(R.id.messageTextView)
        messageTextView.text = message

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 200)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}
