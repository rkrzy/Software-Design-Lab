package com.example.softwaredesign.week5.practice3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week5_ex3_activity_sub)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")
        val extra = intent.getStringExtra("extra")
        val image = intent.getParcelableExtra<Bitmap>("image")

        findViewById<TextView>(R.id.textName).text = name
        findViewById<TextView>(R.id.textAge).text = age
        findViewById<TextView>(R.id.textPhone).text = phone
        findViewById<TextView>(R.id.textAddress).text = address
        findViewById<TextView>(R.id.textExtra).text = extra
        findViewById<ImageView>(R.id.imagePreview).setImageBitmap(image)

        findViewById<Button>(R.id.buttonComplete).setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("name", name)
                putExtra("age", age)
                putExtra("phone", phone)
                putExtra("address", address)
                putExtra("extra", extra)
                putExtra("image", image)
            }
            startActivity(intent)
            setResult(Activity.RESULT_OK)
            finish()
        }

        findViewById<Button>(R.id.buttonEdit).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("name", name)
                putExtra("age", age)
                putExtra("phone", phone)
                putExtra("address", address)
                putExtra("extra", extra)
                putExtra("image", image)
            }
            startActivity(intent)
            finish()
        }
    }
}