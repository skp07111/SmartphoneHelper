package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

//구글어시스턴스 도우미 기능
class Helper : AppCompatActivity() {
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )
    lateinit var b_helper: Button
    lateinit var b_back: Button
    lateinit var b_list: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helper)

        //tts 초기화 설정
        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
            b_helper = findViewById<Button>(R.id.btn_helper)

            tts?.speak(
                "네모 테두리의 버튼들을 눌러주시면 기능을 설명해 드립니다 ", TextToSpeech.QUEUE_FLUSH, null, null
            )

            //
            b_helper.setOnClickListener {
                tts?.speak(
                    "이 버튼은 홈 버튼입니다. 이 버튼을 가볍게 누르면 맨처음 화면으로 돌아갑니다. 만약 이 버튼을 꾹 누르면 보시는 것 같이 도우미 화면이 뜹니다. 도우미에게 지도 앱을 켜달라고 할 수도있고, 오늘 날씨를 물어볼 수 있습니다. 만약 찾으려는 앱이 어디있는지 잘 모르시다면 이 버튼을 꾹 누르시고 그 후에 모르는 걸 물어보세요",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
                val intent = Intent(this, Helper2::class.java)
                startActivity(intent)
            }
            b_back.setOnClickListener {
                tts?.speak(
                    "이 버튼을 누르면 바로 이전 화면으로 되돌아갑니다.",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )

            }
            b_list.setOnClickListener {
                tts?.speak(
                    "이 버튼을 누르면 이전에 봤던 화면들의 목록을 볼 수 있습니다.",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
                val intent = Intent(this, Helper2::class.java)
                startActivity(intent)
            }

        }
    }
}