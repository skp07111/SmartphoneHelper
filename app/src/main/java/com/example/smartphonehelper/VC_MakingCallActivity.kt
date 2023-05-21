package com.example.smartphonehelper

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class VC_MakingCallActivity  : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST_CODE = 1
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraDevice: CameraDevice
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var captureSession: CameraCaptureSession
    private lateinit var previewSurface: Surface
    var tts: TextToSpeech? = null

    private val cameraDeviceStateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            createCameraPreviewSession()
        }

        override fun onDisconnected(camera: CameraDevice) {
            camera.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            camera.close()
            Log.e("VC_MakingCallActivity", "Failed to open camera")
        }
    }

    private val surfaceCallback = object : SurfaceHolder.Callback {
        override fun surfaceCreated(holder: SurfaceHolder) {
            previewSurface = holder.surface
            openCamera()
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            // Do nothing
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            // Do nothing
        }
    }

    lateinit var vc_camera: ImageButton
    lateinit var vc_change: ImageButton
    lateinit var vc_off: ImageButton
    lateinit var vc_block: ImageButton
    lateinit var vc_speaker: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vc_makingcall)

        // vc_camera = findViewById<ImageButton>(R.id.icon_vc_camera1)
        // vc_change = findViewById<ImageButton>(R.id.icon_vc_change1)
        vc_off = findViewById<ImageButton>(R.id.icon_vc_off1)
        // vc_block = findViewById<ImageButton>(R.id.icon_vc_block1)
        // vc_speaker = findViewById<ImageButton>(R.id.icon_vc_speaker1)

        val surfaceView: SurfaceView = findViewById(R.id.surfaceView1)
        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(surfaceCallback)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            openCamera()
        }

        /* 이 코드 추가하면 오류남...
        //3초후 통화 연결 되어 영상통화중 화면(VC_OnThePhoneCallActivity)으로 전환
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, VC_OnThePhoneCallActivity::class.java))
        }, 3000) */

        openTTS()
    }

    private fun openTTS() {
        tts = TextToSpeech(applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Korean language is not supported.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
            //tts 말하기
            tts?.speak(
                "이 기능은 영상통화 기능입니다.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }
    }

    private fun openCamera() {
        try {
            val cameraId = cameraManager.cameraIdList[0]
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            cameraManager.openCamera(cameraId, cameraDeviceStateCallback, null)
        } catch (e: CameraAccessException) {
            Log.e("VC_MakingCallActivity", "Failed to access camera", e)
        }
    }

    private fun createCameraPreviewSession() {
        try {
            cameraDevice.createCaptureSession(listOf(previewSurface), object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    captureSession = session
                    updatePreview()
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {
                    Log.e("VC_MakingCallActivity", "Failed to configure camera capture session")
                }
            }, null)
        } catch (e: CameraAccessException) {
            Log.e("VC_MakingCallActivity", "Failed to create camera capture session", e)
        }
    }

    private fun updatePreview() {
        try {
            val captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder.addTarget(previewSurface)
            captureSession.setRepeatingRequest(captureRequestBuilder.build(), null, null)
        } catch (e: CameraAccessException) {
            Log.e("VC_MakingCallActivity", "Failed to update preview", e)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Log.e("VC_MakingCallActivity", "Camera permission denied")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraDevice.close()
    }
}