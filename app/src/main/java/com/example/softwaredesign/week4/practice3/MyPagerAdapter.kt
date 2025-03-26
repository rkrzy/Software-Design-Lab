package com.example.softwaredesign.week4.practice3

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.softwaredesign.databinding.Week4Ex3ItemPagerBinding

class MyPagerViewHolder(val binding: Week4Ex3ItemPagerBinding) : RecyclerView.ViewHolder(binding.root)

class MyPagerAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyPagerViewHolder(
            Week4Ex3ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding

        binding.itemPagerTextView.text = datas[position]

        // TODO : position에 따라 배경색 변경
        val color = when (position % 3) {
            0 -> Color.RED
            1 -> Color.BLUE
            2 -> Color.GREEN
            else -> Color.GRAY
        }
        binding.itemPager.setBackgroundColor(color)
    }

    override fun getItemCount(): Int = datas.size
}
