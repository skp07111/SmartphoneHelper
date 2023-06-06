package com.npnc.smartphonehelper

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class C_PhoneBook_Search2Activity : AppCompatActivity(){
    private lateinit var popupContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonebook_search2)
        showPopupMessage("빨간 테두리 안의 음성 검색 버튼을 누르고 전화하길 원하는 사람의 이름을 말하면 자동으로 검색됩니다")
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