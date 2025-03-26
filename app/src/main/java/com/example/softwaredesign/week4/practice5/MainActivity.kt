package com.example.softwaredesign.week4.practice5

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.softwaredesign.R
import com.example.softwaredesign.databinding.Week4Ex5ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = Week4Ex5ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "week4_ex5" // 타이틀 표시
        // ✅ TODO: viewpager 추가
        val adapter = MyPagerAdapter(this)
        binding.viewpager.adapter = adapter

        // ✅ drawer 추가
        val drawer = findViewById<DrawerLayout>(R.id.drawer)

        // ✅ TODO: ActionBarDrawerToggle 추가
        toggle = ActionBarDrawerToggle(this, drawer, R.string.drawer_opened, R.string.drawer_closed)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}
