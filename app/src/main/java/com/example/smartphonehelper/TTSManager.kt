package com.example.smartphonehelper

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

object TTSManager {
    private var tts: TextToSpeech? = null

    fun initialize(context: Context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
            }
        }
    }

    fun play(text: String) {
        tts?.let {
            if (it.isSpeaking) {
                it.stop()
            }
            it.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}