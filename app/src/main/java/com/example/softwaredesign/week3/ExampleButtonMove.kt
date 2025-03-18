package com.example.softwaredesign.week3

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class ButtonRelayActivity : ComponentActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week3_button_relay)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

        btn1.setOnClickListener {
            btn1.visibility = View.INVISIBLE
            btn2.visibility = View.VISIBLE
        }

        btn2.setOnClickListener {
            btn2.visibility = View.INVISIBLE
            btn3.visibility = View.VISIBLE
        }

        btn3.setOnClickListener {
            btn3.visibility = View.INVISIBLE
            btn1.visibility = View.VISIBLE
        }
    }
}
