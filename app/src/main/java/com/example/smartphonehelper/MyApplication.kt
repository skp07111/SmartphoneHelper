package com.example.smartphonehelper

import android.app.Application

class MyApplication : Application() {
    lateinit var ttsManager: TTSManager

    override fun onCreate() {
        super.onCreate()
        ttsManager = TTSManager
        // TTSManager 초기화
        ttsManager.initialize(applicationContext)
    }
}
