package com.example.softwaredesign.week4.practice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.softwaredesign.databinding.Week4Ex3ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Week4Ex3ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for (i in 1..3) {
            datas.add("Item $i")
        }

        // TODO: 뷰페이저에 어댑터를 연결, 방향 설정
        val adapter = MyPagerAdapter(datas)
        binding.viewpager.adapter = adapter
        binding.viewpager.orientation = androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
    }
}
