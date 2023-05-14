package com.example.smartphonehelper
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TTSActivity :  AppCompatActivity(){
    var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 초기화
        tts = TextToSpeech(
            applicationContext
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                tts!!.language = Locale.KOREAN
            }
        }

    }
    // 실행 함수
    public fun ttsSpeak(strTTS:String){
        tts?.speak(strTTS, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    public override fun onPause() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onPause()
    }
}