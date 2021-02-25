package com.harsh.databindingwithrecyclerview.ui

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.harsh.databindingwithrecyclerview.R
import com.harsh.databindingwithrecyclerview.base.Constants
import com.harsh.databindingwithrecyclerview.databinding.ActivityImageViewBinding

class ImageViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_image_view)*/

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_image_view
        )

        intent.extras?.let {
            val imageUrl = intent.getStringExtra(Constants.IMAGE_URL).orEmpty()
            if (imageUrl.isNotEmpty()) {
                binding.imageUrl = imageUrl

                scaleGestureDetector = ScaleGestureDetector(this, ScaleDetection())
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }

    inner class ScaleDetection : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor
            if (mScaleFactor > 1 && mScaleFactor < 5) {
                binding.ivZoomable.scaleX = mScaleFactor
                binding.ivZoomable.scaleY = mScaleFactor
            }
            return true
        }

    }
}