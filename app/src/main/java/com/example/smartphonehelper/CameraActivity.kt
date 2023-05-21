package com.example.smartphonehelper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import java.io.IOException

class CameraActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null

    private lateinit var captureButton: Button
    private lateinit var capturedImage: ImageView

    private lateinit var captureMessageContainer: RelativeLayout
    private lateinit var captureMessage: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button

    private val buttonStates: MutableMap<Button, Boolean> = mutableMapOf()
    private var clickedButton: Button? = null

    private val CAMERA_PERMISSION_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val surfaceView = findViewById<SurfaceView>(R.id.cameraPreview)
        captureButton = findViewById(R.id.captureButton)
        capturedImage = findViewById(R.id.capturedImage)

        captureMessageContainer = findViewById(R.id.captureMessageContainer)
        captureMessage = findViewById(R.id.captureMessage)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)

        button1.setOnClickListener {
            updateButtonStates(button1)
            // 버튼 1 클릭 시 팝업창 표시
            showPopupMessage("실제 카메라 어플에서 이 버튼을 누르면 다양한 카메라 설정 기능들이 나타납니다.")
        }

        button2.setOnClickListener {
            updateButtonStates(button2)
            // 버튼 2 클릭 시 팝업창 표시
            showPopupMessage("카메라 플래시(꺼짐/자동/켜짐) 기능을 이용할 수 있는 버튼입니다. 플래시란 순간적으로 강한 빛을 내어서 촬영 장면을 밝히는 것을 말합니다.")
        }

        button3.setOnClickListener {
            updateButtonStates(button3)
            // 버튼 1 클릭 시 팝업창 표시
            showPopupMessage("타이머 기능(켜짐/꺼짐)을 이용할 수 있는 버튼입니다.")
        }

        button4.setOnClickListener {
            updateButtonStates(button4)
            // 버튼 4 클릭 시 팝업창 표시
            showPopupMessage("화면 비율을 설정할 수 있는 버튼입니다.")
        }

        button5.setOnClickListener {
            updateButtonStates(button5)
            // 버튼 1 클릭 시 팝업창 표시
            showPopupMessage("촬영 버튼을 누르는 순간 누르기 전 2초 정도 간의 촬영이 됩니다.")
        }

        button6.setOnClickListener {
            updateButtonStates(button6)
            // 버튼 6 클릭 시 팝업창 표시
            showPopupMessage("사진에 필터를 적용하여 사진 색깔을 다양하게 바꿀 수 있고, 사진을 보정할 수 있습니다.")
        }

        surfaceHolder = surfaceView.holder
        surfaceHolder?.addCallback(this)

        val buttonContainer = findViewById<LinearLayout>(R.id.buttonContainer)
        val glowLayout = findViewById<LinearLayout>(R.id.glowLayout)
        val captureMessageContainer = findViewById<RelativeLayout>(R.id.captureMessageContainer)

        buttonContainer.postDelayed({
            glowLayout.visibility = View.VISIBLE
        }, 1000)

        captureMessageContainer.visibility = View.VISIBLE

        captureButton.setOnClickListener {
            captureImage()
        }
        showCaptureStartMessage()
    }

    private fun updateButtonStates(button: Button) {
        clickedButton = button
        applyGrayFilter()
    }

    private fun applyGrayFilter() {
        for (button in listOf(button1, button2, button3, button4, button5, button6)) {
            val isClicked = button == clickedButton
            if (isClicked) {
                val grayColorWithAlpha = ColorUtils.setAlphaComponent(
                    ContextCompat.getColor(this, android.R.color.darker_gray),
                    128
                )
                val grayColorDrawable = ColorDrawable(grayColorWithAlpha)
                button.background = grayColorDrawable
            } else {
                button.background = null
            }
        }
    }

    private fun closePopupMessage() {
        val popupContainer = findViewById<LinearLayout>(R.id.popupContainer)
        popupContainer.visibility = View.GONE
        applyGrayFilter()

        clickedButton = null
        applyGrayFilter()
    }

    private fun showPopupMessage(message: String) {
        val popupContainer = findViewById<LinearLayout>(R.id.popupContainer)
        val popupMessage = findViewById<TextView>(R.id.popupMessage)
        val closePopupButton = findViewById<Button>(R.id.closePopupButton)

        // 팝업 메시지 창 배경색 설정
        val backgroundColor = ContextCompat.getColor(this, R.color.popupBackgroundColor)
        popupContainer.setBackgroundColor(backgroundColor)

        // 텍스트 색상 설정
        val textColor = ContextCompat.getColor(this, R.color.popupTextColor)
        popupMessage.setTextColor(textColor)

        // 텍스트 크기 설정
        val textSize = resources.getDimension(R.dimen.popupTextSize)
        popupMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        popupMessage.text = message
        popupContainer.visibility = View.VISIBLE

        closePopupButton.setOnClickListener {
            closePopupMessage()
        }
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
        captureMessage.text = "사진 촬영을 하시려면\n하단 중앙에 위치한\n동그란 하얀색 버튼을\n눌러주세요!"
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