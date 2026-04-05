package com.vibhorpatil.composeapplication.paintapp.data

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.vibhorpatil.composeapplication.R

class PaintActivity : ComponentActivity() {

    private lateinit var drawingView: DrawingView
    private lateinit var ibBrushSize: ImageButton
    private lateinit var llColorChooser: LinearLayout
    private lateinit var ibChooseImage: ImageButton
    private lateinit var ibUndoDrawing: ImageButton
    private lateinit var ibRedoDrawing: ImageButton
    private lateinit var ivBackground: ImageView
    private lateinit var mImageButtonCurrentPaint: ImageButton
    private lateinit var requestPermission: ActivityResultLauncher<Array<String>>
    private lateinit var openGalleryLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_paint_activity)
        generateId()
        drawingView.setSizeForBrush(5.toFloat())
        ibBrushSize.setOnClickListener { showBrushSizeChooserDialog() }

        mImageButtonCurrentPaint = llColorChooser[1] as ImageButton

        mImageButtonCurrentPaint.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_selected)
        )
        setClickListeners()
        registerLauncher()
    }

    private fun generateId() {
        drawingView = findViewById(R.id.drawingView)
        ibBrushSize = findViewById(R.id.ibBrushSize)
        llColorChooser = findViewById(R.id.llColorChooser)
        ibChooseImage = findViewById(R.id.ibChooseImage)
        ivBackground = findViewById(R.id.ivBackground)
        ibUndoDrawing = findViewById(R.id.ibUndoDrawing)
        ibRedoDrawing = findViewById(R.id.ibRedoDrawing)
    }

    private fun setClickListeners() {
        ibChooseImage.setOnClickListener {
            requestStoragePermission()
        }
        ibUndoDrawing.setOnClickListener {
            drawingView.undoDrawing()
        }
        ibRedoDrawing.setOnClickListener {
            drawingView.redoDrawing()
        }
    }

    private fun registerLauncher() {
        requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    val permissionName = it.key
                    val isGranted = it.value
                    if (isGranted) {
                        val pickIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        openGalleryLauncher.launch(pickIntent)
                    } else {
                        if (permissionName == Manifest.permission.READ_EXTERNAL_STORAGE) {

                        }
                    }
                }
            }

        openGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK && result.data != null) {
                    ivBackground.setImageURI(result.data?.data)
                }
            }
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush Size")
        val ivSmall = brushDialog.findViewById<ImageView>(R.id.ivSmallSize)
        val ivMedium : ImageView = brushDialog.findViewById(R.id.ivMediumSize)
        val ivLarge = brushDialog.findViewById<ImageView>(R.id.ivLargeSize)

        ivSmall.setOnClickListener { setBrushSize(10, brushDialog) }
        ivMedium.setOnClickListener { setBrushSize(20, brushDialog) }
        ivLarge.setOnClickListener { setBrushSize(30, brushDialog) }

        brushDialog.show()
    }

    fun setBrushSize(brushSize: Int, brushDialog: Dialog) {
        drawingView.setSizeForBrush(brushSize.toFloat())
        brushDialog.dismiss()
    }

    fun paintClicked(view: View) {
        if (view != mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_selected)
            )

            mImageButtonCurrentPaint.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = imageButton
        }
    }

    fun showRationalDialog(title: String, message: String) {
        val rationalDialogBuilder = AlertDialog.Builder(this)

        rationalDialogBuilder.setTitle(title)
            .setMessage(message)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.READ_EXTERNAL_STORAGE)
        ) {
            showRationalDialog("Drawing App", "Need permission to load image from gallery")
        } else {
            requestPermission.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
    }


}