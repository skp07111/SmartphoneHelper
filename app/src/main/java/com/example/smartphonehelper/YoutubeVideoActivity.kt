package com.example.smartphonehelper

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils

class YoutubeVideoActivity : AppCompatActivity() {
    lateinit var subscribeButton : ImageButton
    private var clickedButton: ImageButton? = null

    private lateinit var popupContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video)

        subscribeButton = findViewById<ImageButton>(R.id.icon_subscribe)

        subscribeButton.setOnClickListener {
            updateButtonStates(subscribeButton)
            // 구독 버튼 클릭 시 팝업창 표시
            showPopupMessage("이 버튼을 누르면 해당 채널을 구독할 수 있습니다.")
        }

        showPopupMessage2("빨간색 테두리의 버튼을 눌러보세요!")
    }

    private fun updateButtonStates(button: ImageButton) {
        clickedButton = button
        applyGrayFilter()
    }

    private fun closePopupMessage() {
        val popupContainer = findViewById<LinearLayout>(R.id.popupContainer)
        popupContainer.visibility = View.GONE
        applyGrayFilter()

        clickedButton = null
        applyGrayFilter()
    }

    private fun applyGrayFilter() {
        for (button in listOf(subscribeButton)) {
            val isClicked = button == clickedButton
            if (isClicked) {
                val grayColorWithAlpha = ColorUtils.setAlphaComponent(
                    ContextCompat.getColor(this, android.R.color.darker_gray),
                    128
                )
                val grayColorDrawable = ColorDrawable(grayColorWithAlpha)
                button.background = grayColorDrawable
            } else {
                button.background = null
            }
        }
    }

    private fun showPopupMessage(message: String) {
        val popupContainer = findViewById<LinearLayout>(R.id.popupContainer)
        val popupMessage = findViewById<TextView>(R.id.popupMessage)
        val closePopupButton = findViewById<Button>(R.id.closePopupButton)

        // 팝업 메시지 창 배경색 설정
        val backgroundColor = ContextCompat.getColor(this, R.color.popupBackgroundColor)
        popupContainer.setBackgroundColor(backgroundColor)

        // 텍스트 색상 설정
        val textColor = ContextCompat.getColor(this, R.color.popupTextColor)
        popupMessage.setTextColor(textColor)

        // 텍스트 크기 설정
        val textSize = resources.getDimension(R.dimen.popupTextSize)
        popupMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        popupMessage.text = message
        popupContainer.visibility = View.VISIBLE

        closePopupButton.setOnClickListener {
            closePopupMessage()
        }
    }

    private fun showPopupMessage2(message: String) {
        popupContainer = findViewById(R.id.popupContainer4)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}