package com.example.softwaredesign.week3

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import com.example.softwaredesign.R

class ColorChangeActivity : ComponentActivity() {

    private lateinit var linLayer: LinearLayout
    private lateinit var btnChangeColor: Button
    private var colorIndex = 0
    private val colors = arrayOf("#FF0000", "#00FF00", "#0000FF")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week3_colorchange)

        linLayer = findViewById(R.id.linLayout)
        btnChangeColor = findViewById(R.id.btnChangeColor)

        btnChangeColor.setOnClickListener {
            colorIndex = (colorIndex + 1) % colors.size
            linLayer.setBackgroundColor(Color.parseColor(colors[colorIndex]))
        }
    }
}
