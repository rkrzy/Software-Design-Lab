package com.example.softwaredesign.week5.practice3

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week5_ex3_activity_result)

        val textResult: TextView = findViewById(R.id.textResult)
        val imageResult: ImageView = findViewById(R.id.imageResult)

        val name = intent.getStringExtra("name") ?: ""
        val age = intent.getStringExtra("age") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val extra = intent.getStringExtra("extra") ?: ""
        val image = intent.getParcelableExtra<Bitmap>("image")

        val resultText = "이름: $name\t\t나이: $age\n\n연락처: $phone\n\n주소: $address\n\n기타: $extra"
        textResult.text = resultText
        image?.let { imageResult.setImageBitmap(it) }

        Toast.makeText(this, "정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }
}