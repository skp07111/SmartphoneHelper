package com.example.smartphonehelper

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission_group.CAMERA
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.hardware.SensorPrivacyManager.Sensors.CAMERA
import android.hardware.camera2.*
import android.media.MediaRecorder.VideoSource.CAMERA
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class VC_MakingCallActivity  : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST_CODE = 1
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraDevice: CameraDevice
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var captureSession: CameraCaptureSession
    private lateinit var previewSurface: Surface

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
            Log.e("MirrorAppActivity", "Failed to open camera")
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

    lateinit var vc_camera: Button
    lateinit var vc_change: Button
    lateinit var vc_off: Button
    lateinit var vc_block: Button
    lateinit var vc_speaker: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videocall_makingcall)

        // vc_camera = findViewById<Button>(R.id.icon_vc_camera)
        vc_change = findViewById<Button>(R.id.icon_vc_change)
        vc_off = findViewById<Button>(R.id.icon_vc_off)
        vc_block = findViewById<Button>(R.id.icon_vc_block)
        vc_speaker = findViewById<Button>(R.id.icon_vc_speaker)

        val surfaceView: SurfaceView = findViewById(R.id.surfaceView)
        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(surfaceCallback)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            openCamera()
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
            Log.e("MirrorAppActivity", "Failed to access camera", e)
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
                    Log.e("MirrorAppActivity", "Failed to configure camera capture session")
                }
            }, null)
        } catch (e: CameraAccessException) {
            Log.e("MirrorAppActivity", "Failed to create camera capture session", e)
        }
    }

    private fun updatePreview() {
        try {
            val captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder.addTarget(previewSurface)
            captureSession.setRepeatingRequest(captureRequestBuilder.build(), null, null)
        } catch (e: CameraAccessException) {
            Log.e("MirrorAppActivity", "Failed to update preview", e)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Log.e("MirrorAppActivity", "Camera permission denied")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraDevice.close()
    }
}
