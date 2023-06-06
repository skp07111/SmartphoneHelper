package com.npnc.smartphonehelper

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class C_RecentCallActivity : AppCompatActivity() {
    private lateinit var popupContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recentcall)
        showPopupMessage("최근에 통화한 사람의 목록을 볼 수 있고, 그 사람의 이름을 누르면 바로 통화를 할 수 있습니다.")
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