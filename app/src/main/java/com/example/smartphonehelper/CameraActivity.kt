package com.example.smartphonehelper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException

class CameraActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null

    private lateinit var captureButton: Button
    private lateinit var capturedImage: ImageView

    private lateinit var captureMessageContainer: RelativeLayout
    private lateinit var captureMessage: TextView

    private val CAMERA_PERMISSION_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val surfaceView = findViewById<SurfaceView>(R.id.cameraPreview)
        captureButton = findViewById(R.id.captureButton)
        capturedImage = findViewById(R.id.capturedImage)

        captureMessageContainer = findViewById(R.id.captureMessageContainer)
        captureMessage = findViewById(R.id.captureMessage)

        surfaceHolder = surfaceView.holder
        surfaceHolder?.addCallback(this)

        captureButton.setOnClickListener {
            captureImage()
        }
        showCaptureStartMessage()
    }

    private fun captureImage() {
        camera?.takePicture(null, null, Camera.PictureCallback { data, _ ->
            val bitmap = data?.toBitmap()
            val rotatedBitmap = rotateBitmap(bitmap, 90)
            capturedImage.setImageBitmap(rotatedBitmap)
            capturedImage.visibility = View.VISIBLE
            surfaceHolder?.let { holder ->
                try {
                    camera?.stopPreview()
                    camera?.setPreviewDisplay(holder)
                    camera?.startPreview()
                    showCaptureSuccessMessage()
                    // 5초 후에 이미지를 숨김
                    Handler().postDelayed({
                        capturedImage.visibility = View.GONE
                    }, 5000)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun showCaptureStartMessage() {
        captureMessageContainer.visibility = View.VISIBLE
        captureMessage.text = "사진 촬영을 하시려면\n하단에 위치한 동그란 하얀색 버튼을\n눌러주세요!"
    }

    private fun showCaptureSuccessMessage() {
        captureMessage.visibility = View.VISIBLE
        captureMessage.text = "사진 촬영에 성공하셨습니다!\n잘하셨어요.\n(해당 사진은 연습용으로\n앨범에 저장되지 않습니다.)"

        // 5초 후에 메시지를 숨김
        Handler().postDelayed({
            captureMessage.visibility = View.GONE
        }, 5000)
    }

    private fun rotateBitmap(bitmap: Bitmap?, degrees: Int): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(
            bitmap!!, 0, 0, bitmap.width, bitmap.height, matrix, true
        )
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

        val parameters = camera?.parameters
        val previewSize = getOptimalPreviewSize(parameters, height, width) // 미리보기의 가로세로를 교체하여 설정합니다.
        parameters?.setPreviewSize(previewSize?.width ?: width, previewSize?.height ?: height)

        // 미리보기 회전 설정
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val rotation = when (display.rotation) {
            Surface.ROTATION_0 -> 90
            Surface.ROTATION_90 -> 0
            Surface.ROTATION_180 -> 270
            Surface.ROTATION_270 -> 180
            else -> 0
        }
        camera?.setDisplayOrientation(rotation)

        camera?.parameters = parameters

        try {
            camera?.setPreviewDisplay(surfaceHolder)
            camera?.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getOptimalPreviewSize(
        parameters: Camera.Parameters?,
        width: Int,
        height: Int
    ): Camera.Size? {
        val supportedSizes = parameters?.supportedPreviewSizes
        val targetRatio = width.toDouble() / height
        var optimalSize: Camera.Size? = null
        var minDiff = Double.MAX_VALUE

        for (size in supportedSizes!!) {
            val ratio = size.width.toDouble() / size.height
            val diff = Math.abs(ratio - targetRatio)
            if (diff < minDiff) {
                optimalSize = size
                minDiff = diff
            }
        }

        return optimalSize
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