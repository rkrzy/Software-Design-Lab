package com.example.softwaredesign.week4.practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.softwaredesign.databinding.Week4Ex2RecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Week4Ex2RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for (i in 1..20) {
            datas.add("Item $i")
        }

        // TODO : linearLayoutManager, adapter, itemDecoration 설정
        val layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(datas)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(itemDecoration)
    }
}
