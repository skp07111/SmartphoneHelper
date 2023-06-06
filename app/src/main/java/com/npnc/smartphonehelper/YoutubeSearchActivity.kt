package com.npnc.smartphonehelper

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import java.util.*

class YoutubeSearchActivity : AppCompatActivity() {

    lateinit var back: ImageButton
    lateinit var searchWindow: ImageButton
    lateinit var voice: ImageButton
    private var clickedButton: ImageButton? = null

    private lateinit var popupContainer: RelativeLayout
    var tts: TextToSpeech? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_search)

        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
        }

        back = findViewById<ImageButton>(R.id.icon_back)
        searchWindow = findViewById<ImageButton>(R.id.icon_searchWindow)
        voice = findViewById<ImageButton>(R.id.icon_voice)

        back.setOnClickListener() {
            var intent = Intent(this, YoutubeMainActivity::class.java)
            startActivity(intent)
        }

        searchWindow.setOnClickListener {
            updateButtonStates(searchWindow)
            // 검색창 클릭 시 팝업창 표시
            showPopupMessage("이 창에 검색어를 입력하여 보고싶은 동영상을 찾을 수 있습니다.")
            tts?.speak(
                "이 창에 검색어를 입력하여 보고싶은 동영상을 찾을 수 있습니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        voice.setOnClickListener {
            updateButtonStates(voice)
            // 음성 검색 클릭 시 팝업창 표시
            showPopupMessage("이 버튼을 누르면 음성 인식으로 보고싶은 동영상을 검색할 수 있습니다.")
            tts?.speak(
                "이 버튼을 누르면 음성 인식으로 보고싶은 동영상을 검색할 수 있습니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        showPopupMessage2("빨간색 테두리의 버튼들을 하나씩 눌러보세요!")
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
        for (button in listOf(searchWindow, voice)) {
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
        popupContainer = findViewById(R.id.popupContainer2)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}