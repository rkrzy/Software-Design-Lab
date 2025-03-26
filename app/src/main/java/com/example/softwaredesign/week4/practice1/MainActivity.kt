package com.example.softwaredesign.week4.practice1

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.softwaredesign.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week4_ex1_activity_main)

        // TODO: FragmentManager 및 fragButton 변수 선언
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragButton: Button = findViewById(R.id.fragBut1)

        var onClicked = false
        fragButton.setOnClickListener {
            if (onClicked) {
                onClicked = false
                // TODO: FragmentManager를 사용하여 Fragment를 제거
                val fragment = fragmentManager.findFragmentByTag("frag1")
                if (fragment != null) {
                    val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                    transaction.remove(fragment)
                    transaction.commit()
                }
            } else {
                onClicked = true
                // TODO: FragmentManager를 사용하여 Fragment를 추가
                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                val fragment = OneFragment()
                transaction.add(R.id.fragment_content, fragment, "frag1")
                transaction.commit()
            }
        }
    }
}
