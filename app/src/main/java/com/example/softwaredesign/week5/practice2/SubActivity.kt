package com.example.softwaredesign.week5.practice2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week5_ex2_activity_sub)

        val btn = findViewById<Button>(R.id.btn_sub)
        val ev = findViewById<EditText>(R.id.ev_sub)
        val res = findViewById<TextView>(R.id.res_sub)

        // MainActivity에서 전달된 값 받기
        val received = intent.getStringExtra("data")
        res.text = received + "\n-send from main"

        // 전송 버튼 클릭 → MainActivity로 되돌려보내기
        btn.setOnClickListener {
            val sendBack = ev.text.toString()
            val intent = Intent()
            intent.putExtra("data", sendBack)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
