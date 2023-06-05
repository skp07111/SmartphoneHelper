package com.example.smartphonehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView

class PlayStore2Activity : AppCompatActivity() {
    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_store2)

        showPopupMessage("검색창에서 설치하고 싶은 앱을\n직접 입력할 수 있습니다.\n혹은 왼쪽 상단에 [마이크] 모양 버튼을 눌러서\n음성 검색을 할 수 있어요.\n한 번 눌러보세요.")

        val playStoreMikeIcon = findViewById<ImageButton>(R.id.playStoreMikeIcon)
        playStoreMikeIcon.setOnClickListener {
            val intent = Intent(this, PlayStore3Activity::class.java)
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