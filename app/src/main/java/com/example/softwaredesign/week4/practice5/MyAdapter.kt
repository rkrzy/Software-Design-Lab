package com.example.softwaredesign.week4.practice5

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.softwaredesign.databinding.Week4Ex5ItemMainBinding

class MyViewHolder(val binding: Week4Ex5ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            Week4Ex5ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kkang", "onBindViewHolder : $position")

        val binding = (holder as MyViewHolder).binding

        // ✅ TODO: 데이터를 뷰에 표시
        binding.itemData.text = datas[position]
    }
}
