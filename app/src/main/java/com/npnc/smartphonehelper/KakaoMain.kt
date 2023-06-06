package com.npnc.smartphonehelper

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class KakaoMain : AppCompatActivity() {
    lateinit var btntoprofile: Button

    // tts 권한 설정
    var tts: TextToSpeech? = null
    private val REQUEST_CODE_PERMISSIONS = 1

    //권한 요청 목록
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakaofriends)

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
            tts?.speak(
                "카카오톡 메세지를 보낼 사람을고르는 화면입니다. 하단의 사람 아이콘을 누르면 친구목록을 볼 수 있습니다. " +
                        "상단의 돋보기 아이콘을 누르면 친구목록에서 친구의 이름을 검색할 수 있습니다." +
                        "김연정씨에게 카카오톡을 보내봅시다. 김연정씨를 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null)

        }
        tts?.speak(
            "카카오톡 메세지를 보낼 사람을고르는 화면입니다. 하단의 사람 아이콘을 누르면 친구목록을 볼 수 있습니다. " +
                    "상단의 돋보기 아이콘을 누르면 친구목록에서 친구의 이름을 검색할 수 있습니다." +
                    "김연정씨에게 카카오톡을 보내봅시다. 김연정씨를 눌러보세요.", TextToSpeech.QUEUE_FLUSH, null, null)

        //프로필을 터치하면 프로필화면으로 화면전환
        btntoprofile = findViewById<Button>(R.id.btn_to_profile)
        btntoprofile.setOnClickListener {
            val intent = Intent(this, KakaoProfile::class.java)
            startActivity(intent)
        }

    }
}