package com.example.softwaredesign.week5.practice1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week5_ex1_activity_main)
        val btn: Button = findViewById(R.id.btn_main)

        btn.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
    }
}