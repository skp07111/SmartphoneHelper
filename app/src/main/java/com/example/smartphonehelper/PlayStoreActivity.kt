package com.example.smartphonehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

class PlayStoreActivity : AppCompatActivity() {
    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_store)

        showPopupMessage("검색창에서 설치하고 싶은 앱을 검색할 수 있어요.\n빨간색 테두리 부분을 눌러보세요!")
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