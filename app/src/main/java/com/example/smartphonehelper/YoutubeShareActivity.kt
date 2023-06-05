package com.example.smartphonehelper

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import java.util.*

class YoutubeShareActivity : AppCompatActivity() {
    lateinit var youtubeButton1 : Button
    lateinit var youtubeButton2 : Button
    lateinit var youtubeButton3 : Button
    lateinit var youtubeButton4 : Button
    lateinit var youtubeButton5 : Button
    lateinit var youtubeButton6 : Button
    lateinit var youtubeButton7 : Button
    lateinit var youtubeButton8: Button
    lateinit var youtubeButton9 : Button
    lateinit var youtubeButton10 : Button
    lateinit var youtubeButton11 : Button
    lateinit var youtubeButton12 : Button
    private var clickedButton: Button? = null

    private lateinit var popupContainer: RelativeLayout
    var tts: TextToSpeech? = null
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_share)

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

        youtubeButton1 = findViewById<Button>(R.id.youtube_button1)
        youtubeButton2 = findViewById<Button>(R.id.youtube_button2)
        youtubeButton3 = findViewById<Button>(R.id.youtube_button3)
        youtubeButton4 = findViewById<Button>(R.id.youtube_button4)
        youtubeButton5 = findViewById<Button>(R.id.youtube_button5)
        youtubeButton6 = findViewById<Button>(R.id.youtube_button6)
        youtubeButton7 = findViewById<Button>(R.id.youtube_button7)
        youtubeButton8 = findViewById<Button>(R.id.youtube_button8)
        youtubeButton9 = findViewById<Button>(R.id.youtube_button9)
        youtubeButton10 = findViewById<Button>(R.id.youtube_button10)
        youtubeButton11 = findViewById<Button>(R.id.youtube_button11)
        youtubeButton12 = findViewById<Button>(R.id.youtube_button12)

        youtubeButton1.setOnClickListener {
            updateButtonStates(youtubeButton1)
            // 버튼 1 클릭 시 팝업창 표시
            showPopupMessage("유튜브 동영상의 링크를 복사하는 버튼입니다. 링크를 복사하여 원하는 영상을 다른 사람들에게 공유해보세요!")
            tts?.speak(
                "유튜브 동영상의 링크를 복사하는 버튼입니다. 링크를 복사하여 원하는 영상을 다른 사람들에게 공유해보세요!",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton2.setOnClickListener {
            updateButtonStates(youtubeButton2)
            // 버튼 2 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 카카오톡으로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 카카오톡으로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton3.setOnClickListener {
            updateButtonStates(youtubeButton3)
            // 버튼 3 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 카카오톡 '나와의 채팅방'에 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 카카오톡 나와의 채팅방에 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton4.setOnClickListener {
            updateButtonStates(youtubeButton4)
            // 버튼 4 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 Gmail로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 Gmail로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton5.setOnClickListener {
            updateButtonStates(youtubeButton5)
            // 버튼 5 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 블루투스로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 블루투스로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton6.setOnClickListener {
            updateButtonStates(youtubeButton6)
            // 버튼 6 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 Nearby Share로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 Nearby Share로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton7.setOnClickListener {
            updateButtonStates(youtubeButton7)
            // 버튼 7 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 인스타그램 다이렉트 메시지(DM)로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 인스타그램 다이렉트 메시지(DM)로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton8.setOnClickListener {
            updateButtonStates(youtubeButton8)
            // 버튼 8 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 메시지로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 메시지로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }
        youtubeButton9.setOnClickListener {
            updateButtonStates(youtubeButton9)
            // 버튼 9 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 삼성노트의 새 노트에 추가할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 삼성노트의 새 노트에 추가할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton10.setOnClickListener {
            updateButtonStates(youtubeButton10)
            // 버튼 10 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 삼성노트의 기존 노트에 추가할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 삼성노트의 기존 노트에 추가할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton11.setOnClickListener {
            updateButtonStates(youtubeButton11)
            // 버튼 11 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 이메일로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 이메일로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        youtubeButton12.setOnClickListener {
            updateButtonStates(youtubeButton12)
            // 버튼 12 클릭 시 팝업창 표시
            showPopupMessage("동영상 링크를 Wi-Fi 다이렉트로 다른 사람들에게 공유할 수 있는 버튼입니다.")
            tts?.speak(
                "동영상 링크를 Wi-Fi 다이렉트로 다른 사람들에게 공유할 수 있는 버튼입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        showPopupMessage2("빨간색 테두리의 버튼들을 하나씩 눌러보세요!")
    }

    private fun updateButtonStates(button: Button) {
        clickedButton = button
        applyGrayFilter()
    }

    private fun applyGrayFilter() {
        for (button in listOf(youtubeButton1, youtubeButton2, youtubeButton3, youtubeButton4, youtubeButton5, youtubeButton6, youtubeButton7, youtubeButton8, youtubeButton9, youtubeButton10, youtubeButton11, youtubeButton12)) {
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

    private fun closePopupMessage() {
        val popupContainer = findViewById<LinearLayout>(R.id.popupContainer)
        popupContainer.visibility = View.GONE
        applyGrayFilter()

        clickedButton = null
        applyGrayFilter()
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
        popupContainer = findViewById(R.id.popupContainer5)
        val messageTextView = findViewById<TextView>(R.id.messageTextView)

        messageTextView.text = message
        popupContainer.visibility = View.VISIBLE
    }
}