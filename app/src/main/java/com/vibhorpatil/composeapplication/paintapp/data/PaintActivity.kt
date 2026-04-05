package com.vibhorpatil.composeapplication.paintapp.data

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.vibhorpatil.composeapplication.R

class PaintActivity : ComponentActivity() {

    private lateinit var drawingView: DrawingView
    private lateinit var ibBrushSize: ImageButton
    private lateinit var llColorChooser: LinearLayout
    private lateinit var mImageButtonCurrentPaint: ImageButton

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
    }

    private fun generateId() {
        drawingView = findViewById(R.id.drawingView)
        ibBrushSize = findViewById(R.id.ibBrushSize)
        llColorChooser = findViewById(R.id.llColorChooser)
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
            drawingView?.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_selected)
            )

            mImageButtonCurrentPaint.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = imageButton
        }
    }


}