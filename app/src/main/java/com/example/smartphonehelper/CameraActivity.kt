package com.example.smartphonehelper

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException

class CameraActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null

    private lateinit var captureButton: Button
    private lateinit var capturedImage: ImageView

    private val CAMERA_PERMISSION_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val surfaceView = findViewById<SurfaceView>(R.id.cameraPreview)
        captureButton = findViewById(R.id.captureButton)
        capturedImage = findViewById(R.id.capturedImage)

        surfaceHolder = surfaceView.holder
        surfaceHolder?.addCallback(this)

        captureButton.setOnClickListener {
            captureImage()
        }
    }

    private fun captureImage() {
        camera?.takePicture(null, null, Camera.PictureCallback { data, _ ->
            val bitmap = data?.toBitmap()
            capturedImage.setImageBitmap(bitmap)
            capturedImage.visibility = View.VISIBLE
            surfaceHolder?.let { holder ->
                try {
                    val surface = holder.surface
                    camera?.stopPreview()
                    camera?.setPreviewDisplay(holder)
                    camera?.startPreview()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun ByteArray.toBitmap(): Bitmap? {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST
            )
        }
    }

    override fun surfaceChanged(
        holder: SurfaceHolder,
        format: Int,
        width: Int,
        height: Int
    ) {
        if (surfaceHolder?.surface == null) {
            return
        }
        try {
            camera?.stopPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            camera?.setPreviewDisplay(surfaceHolder)
            camera?.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        releaseCamera()
    }

    private fun openCamera() {
        try {
            camera = Camera.open()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun releaseCamera() {
        camera?.release()
        camera = null
    }
}