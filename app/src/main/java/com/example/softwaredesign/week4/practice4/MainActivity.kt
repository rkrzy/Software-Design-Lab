package com.example.softwaredesign.week4.practice4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.softwaredesign.databinding.Week4Ex4ActivityMainBinding
import com.example.softwaredesign.R

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = Week4Ex4ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        // 햄버거 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Drawer 상태를 ActionBar와 동기화
        toggle.syncState()

        // DrawerLayout에 toggle 리스너 연결
        binding.drawer.addDrawerListener(toggle)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO : Drawer 제어
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
