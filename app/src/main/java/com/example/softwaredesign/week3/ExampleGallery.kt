package com.example.softwaredesign.week3

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.example.softwaredesign.R

class GalleryActivity : ComponentActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnLeft: Button
    private lateinit var btnRight: Button

    private val images = arrayOf(
        R.drawable.placeholder, R.drawable.b, R.drawable.c, R.drawable.d,
        R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
        R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l,
        R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p,
        R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,
        R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x,
        R.drawable.y, R.drawable.z
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week3_gallery)

        imageView = findViewById(R.id.imageView)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)

        // 첫 번째 이미지 표시
        imageView.setImageResource(images[currentIndex])

        btnLeft.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) images.size - 1 else currentIndex - 1
            imageView.setImageResource(images[currentIndex])
        }

        btnRight.setOnClickListener {
            currentIndex = (currentIndex + 1) % images.size
            imageView.setImageResource(images[currentIndex])
        }
    }
}
