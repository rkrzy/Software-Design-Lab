package com.example.softwaredesign.week6.practice3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.databinding.Week6Ex3ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = Week6Ex3ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "StopWatch"
        var isrunning = false
        var ispause = false
        val channel = Channel<String>()
        var h = 0; var m = 0; var s = 0
        var job: Job? = null

        binding.time.text = "10:00:00"
        binding.start.setOnClickListener {
            if (!isrunning) {
                isrunning = true
                ispause = false
                job = GlobalScope.launch {
                    while (isrunning && !ispause) {
                        delay(1000)
                        s++
                        if (s == 60) {
                            s = 0; m++
                        }
                        if (m == 60) {
                            m = 0; h++
                        }
                        val timeStr = String.format("%02d:%02d:%02d", h, m, s)
                        channel.send(timeStr)
                    }
                }
            }
        }

        binding.stop.setOnClickListener {
            isrunning = false
            ispause = false
            job?.cancel()
            h = 0; m = 0; s = 0
            binding.time.text = "00:00:00"
//            Toast.makeText(this, "타이머 정지", Toast.LENGTH_SHORT).show()
        }

        binding.pause.setOnClickListener {
            if (isrunning && !ispause) {
                ispause = true
                job?.cancel()
//                Toast.makeText(this, "일시정지", Toast.LENGTH_SHORT).show()
            }
        }

        binding.resume.setOnClickListener {
            if (isrunning && ispause) {
                ispause = false
                job = GlobalScope.launch {
                    while (isrunning && !ispause) {
                        delay(1000)
                        s++
                        if (s == 60) {
                            s = 0; m++
                        }
                        if (m == 60) {
                            m = 0; h++
                        }
                        val timeStr = String.format("%02d:%02d:%02d", h, m, s)
                        channel.send(timeStr)
                    }
                }
//                Toast.makeText(this, "재시작", Toast.LENGTH_SHORT).show()
            }
        }

        val mainScope = GlobalScope.launch(Dispatchers.Main) {
            channel.consumeEach {
                binding.time.text = it
            }
        }
    }
}
