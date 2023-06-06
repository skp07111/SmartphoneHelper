package com.npnc.smartphonehelper

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class GalleryShareActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_gallery_share)

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
            tts?.speak("공유하고 싶은 상대의 카카오톡 프로필이나 메시지를 선택하시면 사진을 보낼 수 있어요. 아니면 하단 영역에서 손가락을 화면에 댄 채로 왼쪽으로 이동하시면 더보기 라는 버튼이 나옵니다. 더보기 버튼을 누르면 공유할 수 있는 어플이 많이 나와요.", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }
}