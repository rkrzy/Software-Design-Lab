package com.example.softwaredesign.week9.practice2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.softwaredesign.R
import com.example.softwaredesign.databinding.Week9Ex2ActivityReservedBinding

class ReservedActivity : AppCompatActivity() {

    private lateinit var binding: Week9Ex2ActivityReservedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Week9Ex2ActivityReservedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        processIntent(intent)

        binding.viewbutton.setOnClickListener {
            finish()
        }
    }

    private fun processIntent(intent: Intent?) {
        val movies = intent?.getSerializableExtra("movies") as? ArrayList<ReservedMovie>
        val movie = movies?.getOrNull(0)

        if (movie != null) {
            if (!movie.poster_image.isNullOrEmpty()) {
                binding.posterImageView.setImageURI(Uri.parse(movie.poster_image))
                binding.posterImageView.visibility = View.VISIBLE
            } else {
                binding.posterImageView.visibility = View.GONE
            }

            binding.reservedInput1.text = movie.name
            binding.reservedInput2.text = movie.reserved_time
            binding.reservedInput3.text = movie.director
            binding.reservedInput4.text = movie.synopsis
        } else {
            binding.reservedInput1.text = "null"
            binding.reservedInput2.text = "null"
            binding.reservedInput3.text = "null"
            binding.reservedInput4.text = "null"
        }
    }
}
